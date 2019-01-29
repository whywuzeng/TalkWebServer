package com.bhome.web.talk.bean.db;

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
@Entity
@Table(name = "TB_APPLY")
public class Apply {

    public static final int TYPE_APPLY_USER = 10001; //添加好友
    public static final int GETTYPE_APPLY_GROUP= 10002; //添加群

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @PrimaryKeyJoinColumn
    @Column(nullable = false,updatable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "applicationId")
    private User application;
    @Column(updatable = false,insertable = false)
    private String applicationId;

    @Column(columnDefinition = "TEXT")
    private String attach;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();

    @Column()
    private String decription;

    //不必建立外键
    @Column(nullable = false)
    private String targetId;

    @Column(nullable = false)
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getApplication() {
        return application;
    }

    public void setApplication(User application) {
        this.application = application;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
