package com.bhome.web.talk.service;

import com.bhome.web.talk.bean.api.base.ResponseModel;
import com.bhome.web.talk.bean.api.message.MessageModel;
import com.bhome.web.talk.bean.card.MessageCard;
import com.bhome.web.talk.bean.db.Message;
import com.bhome.web.talk.bean.db.User;
import com.bhome.web.talk.factory.MessageFactory;
import com.bhome.web.talk.factory.PushFactory;
import com.bhome.web.talk.factory.UserFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static com.bhome.web.talk.bean.api.base.ResponseModel.ERROR_CREATE_FAILUE;

/**
 * Created by Administrator on 2019-01-30.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.service
 */
@Path("/msg")
public class MessageService extends BaseService{

    // 发送一条消息到服务器
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<MessageCard> message(MessageModel model){

        if (!MessageModel.check(model))
        {
            return ResponseModel.buildParameterError();
        }

        User self = getSelf();

        Message message = MessageFactory.findById(model.getId());
        if (message!=null)
        {
            return ResponseModel.buildOK(new MessageCard(message));
        }

        if(model.getReceiverType() == Message.receiver_group)
        {
            return pushToGroup(model,self);
        }else {
            return pushToUser(model,self);
        }
    }

    // 发送到人
    private ResponseModel<MessageCard> pushToUser(MessageModel model, User self) {

        User receiver = UserFactory.findByUserId(model.getReceiverId());
        if (receiver==null)
        {
            return ResponseModel.noFoundError();
        }

        if (self.getId().equalsIgnoreCase(receiver.getId()))
        {
            return ResponseModel.catchErrorStr(ResponseModel.ERROR_MSGUSER_ISSAME,"sender and receiver is same!");
        }

        Message message = MessageFactory.add(self, receiver, model);

        return pushMessageResponse(self,message);
    }

    // 发送到群
    private ResponseModel<MessageCard> pushToGroup(MessageModel model, User self) {

        return null;
    }

    // 推送并构建一个返回信息
    private ResponseModel<MessageCard> pushMessageResponse(User self, Message message) {

        if (message == null) {
            return ResponseModel.catchErrorStr(ERROR_CREATE_FAILUE, "create message for database failure");
        }
        PushFactory.pushMessage(self, message);

        return ResponseModel.buildOK(new MessageCard(message));
    }
}
