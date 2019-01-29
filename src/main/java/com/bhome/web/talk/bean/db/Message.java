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

/**
 * Created by Administrator on 2019-01-29.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.bean.db
 */
@Entity(name = "TB_MESSAGE")
public class Message {

    public static final int type_unknow = 10001;
    public static final int type_text = 10002;
    public static final int type_audio = 10003;
    public static final int type_pic = 10004;
    public static final int type_file = 10005;


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
}
