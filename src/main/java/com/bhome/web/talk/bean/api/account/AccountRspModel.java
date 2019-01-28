package com.bhome.web.talk.bean.api.account;

import com.bhome.web.talk.bean.card.UserCard;
import com.bhome.web.talk.bean.db.User;
import com.google.gson.annotations.Expose;

/**
 * Created by Administrator on 2019-01-28.
 * <p>
 * by author wz
 * 账号部分返回的Model
 * <p>
 * com.bhome.web.talk.bean.api.account
 */

public class AccountRspModel {
    //用户基本信息
    @Expose
    private String name;

    @Expose
    private String token;

    @Expose
    private UserCard result;

    @Expose
    private boolean isBind;

    public AccountRspModel(User user)
    {
        this(user,false);
    }

    public AccountRspModel(User user,boolean isBind) {
        this.name = user.getName();
        this.token = user.getToken();
        this.result= new UserCard(user);
        this.isBind=isBind;
    }
}
