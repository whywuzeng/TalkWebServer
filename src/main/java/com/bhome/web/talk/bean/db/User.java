package com.bhome.web.talk.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author qiujuer
 */
@Entity
@Table(name = "TB_USER")
public class User {
    //一个主键
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(updatable = false,nullable = false)
    private String id;

    //定义为创建的时间戳m,在创建时就写入
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column
    private String description;

    @Column(nullable = true)
    private LocalDateTime lastReceivedAt = LocalDateTime.now();

    //唯一
    @Column(nullable = false,unique = true,length = 128)
    private String name;

    @Column(nullable = false,unique = true,length = 128)
    private String phone;

    //头像允许为空
    @Column
    private String portrait;

    @Column
    private String pushId;

    @Column(nullable = false)
    private int sex = 0;

    //可以拉去用户信息
    @Column(unique = true)
    private String token;

    @Column
    private LocalTime updateAt;
}
