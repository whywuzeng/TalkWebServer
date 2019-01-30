package com.bhome.web.talk.bean.card;

import com.bhome.web.talk.bean.db.Message;
import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2019-01-30.
 * <p>
 * by author wz
 * 用来发送的数据。记录起来转换为string
 * <p>
 * com.bhome.web.talk.bean.card
 */

public class MessageCard {
    @Expose
    private String id;
    @Expose
    private String attach;
    @Expose
    private LocalDateTime createAt;
    @Expose
    private String groupId;//可以为空

    @Expose
    private String context;

    /**
     * 不允许为空
     */
    @Expose
    private String receiverId;
    @Expose
    private String senderId;
    @Expose
    private int type;

    public MessageCard(Message message) {
        this.id = message.getId();
        this.attach = message.getAttach();
        this.createAt = message.getCreatedAt();
        this.groupId = message.getGroupId();
        this.context = message.getContext();
        this.receiverId = message.getReceiverId();
        this.senderId = message.getSenderId();
        this.type = message.getType();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
