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

public class RegisterModel {
    @Expose
    private String name;
    @Expose
    private String account;
    @Expose
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 检验
     */
    public static boolean check(RegisterModel model)
    {
        return model!=null
                &&! Strings.isNullOrEmpty(model.account)
                &&! Strings.isNullOrEmpty(model.name)
                &&! Strings.isNullOrEmpty(model.phone);

    }
}
