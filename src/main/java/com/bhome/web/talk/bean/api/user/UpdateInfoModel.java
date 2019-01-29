package com.bhome.web.talk.bean.api.user;

import com.bhome.web.talk.bean.db.User;
import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

/**
 * Created by Administrator on 2019-01-29.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.bean.api
 */

public class UpdateInfoModel {

    @Expose
    private String name;
    @Expose
    private String portrait;
    @Expose
    private String desc;
    @Expose
    private int sex = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public User updataInfo(User user){
        if (!Strings.isNullOrEmpty(getName()))
        {
            user.setName(getName());
        }

        if (!Strings.isNullOrEmpty(getPortrait()))
        {
            user.setPortrait(getPortrait());
        }

        if (!Strings.isNullOrEmpty(getDesc()))
        {
            user.setDescription(getDesc());
        }

        if (getSex()!=0)
        {
            user.setSex(getSex());
        }

        return user;
    }

    public static boolean check(UpdateInfoModel model)
    {
        return model!=null
                && (!Strings.isNullOrEmpty(model.getName())
                ||!Strings.isNullOrEmpty(model.getPortrait())
                ||!Strings.isNullOrEmpty(model.getDesc())
                ||model.getSex()!=0);
    }
}
