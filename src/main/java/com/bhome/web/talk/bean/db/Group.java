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

/**
 * Created by Administrator on 2019-01-29.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.bean.db
 */
@Entity(name = "TB_GROUP")
public class Group {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @PrimaryKeyJoinColumn
    @Column(nullable = false,updatable = false)
    private String id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(nullable = false)
    private String description;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String pictrue;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt =LocalDateTime.now();
    // 群的创建者
    // optional: 可选为false，必须有一个创建者
    // fetch: 加载方式FetchType.EAGER，急加载，
    // 意味着加载群的信息的时候就必须加载owner的信息
    // cascade：联级级别为ALL，所有的更改（更新，删除等）都将进行关系更新
    @ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "ownerId")
    private User owner;
    @Column(nullable = false,updatable = false,insertable = false)
    private String ownerId;
}
