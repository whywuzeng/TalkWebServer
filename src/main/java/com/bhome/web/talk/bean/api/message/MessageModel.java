package com.bhome.web.talk.bean.api.message;

import com.bhome.web.talk.bean.db.Message;
import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

/**
 * Created by Administrator on 2019-01-30.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.bean.api.message
 */

public class MessageModel {

    //uuid
    @Expose
    private String id;

    @Expose
    private String context;
    @Expose
    private String receiverId;

    @Expose
    private int receiverType = Message.receiver_noknow;

    @Expose
    private int type = Message.type_text;
    @Expose
    private String attach;

    public static boolean check(MessageModel model)
    {
        return model!=null
                && !Strings.isNullOrEmpty(model.getContext())
                &&! Strings.isNullOrEmpty(model.getReceiverId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public int getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(int receiverType) {
        this.receiverType = receiverType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
