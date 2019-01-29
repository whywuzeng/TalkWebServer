package com.bhome.web.talk.service;

import com.bhome.web.talk.bean.db.User;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by Administrator on 2019-01-29.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.service
 */

public class BaseService {

    @Context
    private SecurityContext mSecurityContext;

    public User getSelf() {
        User user = (User) mSecurityContext.getUserPrincipal();
        return user;
    }
}
