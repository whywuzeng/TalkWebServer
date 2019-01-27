package com.bhome.web.talk.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private User origin;


    //被关注的人
    @ManyToOne(optional = false)
    private User follower;

    @Column
    private String alies;


    //定义为创建的时间戳m,在创建时就写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createTime = LocalDateTime.now();

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updateTime = LocalDateTime.now();


}
