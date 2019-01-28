package com.bhome.web.talk.bean.api.account;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

/**
 * Created by Administrator on 2019-01-28.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.bean.api.account
 */

public class LoginModel {
    @Expose
    private String name;
    @Expose
    private String account;
    @Expose
    private String pushId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public static boolean check(LoginModel model) {
        return model != null
                && !Strings.isNullOrEmpty(model.getAccount())
                && !Strings.isNullOrEmpty(model.getName());
    }
}
