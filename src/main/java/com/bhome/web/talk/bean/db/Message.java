package com.bhome.web.talk.bean.db;

import com.bhome.web.talk.bean.api.message.MessageModel;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Administrator on 2019-01-29.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.bean.db
 */
@Entity()
@Table(name = "TB_MESSAGE")
public class Message {

    public static final int type_unknow = 10001;
    public static final int type_text = 10002;
    public static final int type_audio = 10003;
    public static final int type_pic = 10004;
    public static final int type_file = 10005;

    public static final int receiver_user = 20001;
    public static final int receiver_group = 20002;
    public static final int receiver_noknow = 20003;


    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String attach;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String context;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;
    @Column(updatable = false,insertable = false)
    private String groupId;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiver;
    @Column(nullable = false, insertable = false)
    private String receiverId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "senderId")
    private User sender;
    @Column(updatable = false, nullable = false, insertable = false)
    private String senderId;

    //默认值
    @Column(nullable = false, updatable = false)
    private int type;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    public Message(User self, User receiver, MessageModel model) {
        this.sender=self;
        this.senderId=self.getId();
        this.receiver=receiver;
        this.receiverId=receiver.getId();
        this.id=model.getId();
        this.attach=model.getAttach();
        this.context=model.getContext();
        this.type=model.getType();
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
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

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
