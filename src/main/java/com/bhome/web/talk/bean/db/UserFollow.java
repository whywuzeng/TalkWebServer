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
 * Created by Administrator on 2019/1/27.
 * <p>
 * by author wz
 * 用于联系  用户关注 的中间表
 * <p>
 * com.bhome.web.talk.bean.db
 */
@Entity
@Table(name = "TB_USER_FOLLOW")
public class UserFollow {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(updatable = false,nullable = false)
    private String id;

    //发起人
    //这里 一个user  对应多个 userfollow
    @ManyToOne(optional = false)
    @JoinColumn(name = "originId")
    private User origin;
    //把这个列提取到我们的model中，不允许为null，不允许更新，不允许插入
    @Column(nullable = false,updatable = false,insertable = false)
    private String originId;

    //被关注的人
    @ManyToOne(optional = false)
    @JoinColumn(name = "followerId")
    private User follower;
    //不允许为空，不允许更新，不允许插入
    @Column(nullable = false,updatable = false,insertable = false)
    private String followerId;

    @Column
    private String alies;


    //定义为创建的时间戳m,在创建时就写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createTime = LocalDateTime.now();

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updateTime = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }

    public String getAlies() {
        return alies;
    }

    public void setAlies(String alies) {
        this.alies = alies;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
