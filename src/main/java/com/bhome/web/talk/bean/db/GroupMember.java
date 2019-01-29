package com.bhome.web.talk.bean.db;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "TB_GROUPMEMBER")
public class GroupMember {

    public static final int TYPE_PERMISSION_NONE = 10001; //默认成员权限
    public static final int TYPE_PERMISSION_CREATE = 10002; //创建者
    public static final int TYPE_PERMISSION_ADMIN = 10003; //管理员

    public static final int TYPE_NOTIFY_LEVEL_NONE = 20001;//默认消息
    public static final int TYPE_NOTIFY_LEVEL_INVALID = 20002; //不接受消息
    public static final int TYPE_NOTIFY_LEVEL_CLOSE = 200003; //接受消息不通知

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(nullable = false,updatable = false)
    private String id;

    @Column
    private String alias;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "groupId")
    private Group group;
    @Column(nullable = false,updatable = false,insertable = false)
    private String groupId;

    @Column(nullable = false)
    private int notityLevel = TYPE_NOTIFY_LEVEL_NONE;

    @Column(nullable = false)
    private int permissionType = TYPE_PERMISSION_NONE;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt= LocalDateTime.now();

    @ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;
    @Column(nullable = false,updatable = false,insertable = false)
    private String userId;
}
