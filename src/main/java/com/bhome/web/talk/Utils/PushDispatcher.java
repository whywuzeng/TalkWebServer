package com.bhome.web.talk.Utils;

import com.bhome.web.talk.bean.api.base.PushModel;
import com.bhome.web.talk.bean.db.User;
import com.gexin.rp.sdk.base.IBatch;
import com.gexin.rp.sdk.base.IIGtPush;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.google.common.base.Strings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2019-01-30.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.Utils
 */

public class PushDispatcher {

    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
    private static String appId = "";
    private static String appKey = "";
    private static String masterSecret = "";

    static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    private  IBatch batch;

    private List<BaseBatch> batches =new ArrayList<>();

    public PushDispatcher() {
        IIGtPush push = new IGtPush(host, appKey, masterSecret);
        this.batch = push.getBatch();
    }

    public void addMessage(User receiver, PushModel model){
        if (receiver == null||model == null || Strings.isNullOrEmpty(receiver.getPushId()))
            return;

        if (Strings.isNullOrEmpty(model.getEntityString()))
            return;

        BaseBatch baseBatch = buildMessage(model.getEntityString(), receiver.getId());
        batches.add(baseBatch);
    }

    public boolean submit(){

        boolean ishaving =false;

        for (BaseBatch baseBatch : batches) {
            try {
                batch.add(baseBatch.singleMessage,baseBatch.target);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ishaving =true;
        }
        if (!ishaving)
            return false;

        IPushResult result = null;
        try {
             result = batch.submit();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                batch.retry();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (result!=null)
        {
            Logger.getLogger("PushDispacher")
                    .log(Level.INFO,(String)result.getResponse().get("result"));
            return true;
        }

        Logger.getLogger("PushDispacher")
                .log(Level.WARNING,"服务发送推送处有异常");

        return false;
    }

    public BaseBatch buildMessage(String context, String receiverId)
    {
        SingleMessage message = new SingleMessage();
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionContent(context);
        template.setTransmissionType(0); // 这个Type为int型，填写1则自动启动app

        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(24*60*60*1000);

        // 设置推送目标，填入appid和clientId
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(receiverId);
        return new BaseBatch(message,target);
    }

    private static class BaseBatch{
        private SingleMessage singleMessage;

        private Target target;

        public BaseBatch(SingleMessage singleMessage, Target target) {
            this.singleMessage = singleMessage;
            this.target = target;
        }
    }

    //    private static void constructClientLinkMsg(String cid, String msg, IBatch batch) throws Exception {
//
//        SingleMessage message = new SingleMessage();
//        LinkTemplate template = new LinkTemplate();
//        template.setAppId(appId);
//        template.setAppkey(appKey);
//        template.setTitle("title");
//        template.setText("msg");
//        template.setLogo("push.png");
//        template.setLogoUrl("logoUrl");
//        template.setUrl("url");
//
//        message.setData(template);
//        message.setOffline(true);
//        message.setOfflineExpireTime(1 * 1000);
//
//        // 设置推送目标，填入appid和clientId
//        Target target = new Target();
//        target.setAppId(appId);
//        target.setClientId(cid);
//        batch.add(message, target);
//    }
}
