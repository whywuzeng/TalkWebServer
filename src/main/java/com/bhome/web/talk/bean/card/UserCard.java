package com.bhome.web.talk.bean.card;

import com.bhome.web.talk.bean.db.User;
import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;

/**
 * Created by Administrator on 2019-01-28.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.bean.card
 */

public class UserCard {
    @Expose
    private String id;
    @Expose
    private String phone;
    @Expose
    private String name;
    @Expose
    private String portrait;
    @Expose
    private String desc;
    @Expose
    private int sex = 0;

    @Expose
    private boolean isFollow;

    @Expose
    private int follows;

    @Expose
    private int following;

    @Expose
    private LocalDateTime modifyAt;

    public UserCard(final User user){
        this(user, false);
    }

    public UserCard(User user, boolean isfollow) {
        this.isFollow=isfollow;

        this.id=user.getId();
        this.phone=user.getPhone();
        this.name=user.getName();
        this.portrait=user.getPortrait();
        this.desc=user.getDescription();
        this.sex=user.getSex();

        // TODO 得到关注人和粉丝的数量
        // user.getFollowers().size()
        // 懒加载会报错，因为没有Session
    }
}
