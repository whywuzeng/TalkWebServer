package com.bhome.web.talk.service;


import com.bhome.web.talk.bean.api.account.AccountRspModel;
import com.bhome.web.talk.bean.api.account.LoginModel;
import com.bhome.web.talk.bean.api.account.RegisterModel;
import com.bhome.web.talk.bean.api.base.ResponseModel;
import com.bhome.web.talk.bean.db.User;
import com.bhome.web.talk.factory.UserFactory;
import com.google.common.base.Strings;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author qiujuer
 */
// 127.0.0.1/api/account/...
@Path("/account")
public class AccountService {

    //GET 127.0.0.1/api/account/login
    @GET
    @Path("/login")
    public String get() {
        return "You get the login111111.";
    }


    //POST 127.0.0.1/api/account/login
    @POST
    @Path("/login")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> post(LoginModel model) {
        if (!LoginModel.check(model))
        {
            return ResponseModel.buildParameterError();
        }

        User user = UserFactory.login(model.getName(), model.getAccount());
        if (user!=null)
        {
            //用户存在

            //todo 如果存在pushId
            if (!Strings.isNullOrEmpty(model.getPushId()))
            {
                return bind(user,model.getPushId());
            }

            AccountRspModel accountRspModel = new AccountRspModel(user);
            return ResponseModel.buildOK(accountRspModel);
        }else {
            return ResponseModel.loginError();
        }
    }

    /**
     * 注册接口
     * @param model
     * @return
     */
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> register(RegisterModel model) {
        if (!RegisterModel.check(model)) {
            return ResponseModel.buildParameterError();
        }

        User user = UserFactory.findByName(model.getName());
        if (user!=null)
        {
            //存在相同
            return ResponseModel.HavingUserError();
        }

        user = UserFactory.findByPhone(model.getPhone());
        //已有手机
        if (user!=null)
        {
            return ResponseModel.HavingPhoneError();
        }

        user = UserFactory.register(model.getAccount(), model.getPhone(), model.getName());

        //返回新建账号
        if (user!=null)
        {
            //todo 绑定pushID 逻辑

            //成功返回
            AccountRspModel model1 = new AccountRspModel(user);

            return ResponseModel.buildOK(model1);
        }else {
            return ResponseModel.registerError();
        }
    }

    private static ResponseModel<AccountRspModel> bind(User self,String pushId)
    {
        User user = UserFactory.bind(self, pushId);

        if (user==null)
        {
            //服务器出错  没有添加到数据库里去
            return ResponseModel.serverError();
        }

        AccountRspModel model = new AccountRspModel(user);
        return ResponseModel.buildOK(model);
    }
}
