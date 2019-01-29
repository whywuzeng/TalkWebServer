package com.bhome.web.talk.bean.api.base;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by Administrator on 2019-01-28.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.bean.api.base
 */

public class ResponseModel<M> implements Serializable {

    private static final int SUCEED = 1;

    private static final int ERROR_UNKNOW =0;

    //4001
    public static final int ERROR_PARAMETERS = 4001;


    //相同name 用户名
    public static final int ERROR_HAVINGUSERERROR = 5001;
    //相同phone电话号
    public static final int ERROR_HAVINGPHONE =5002;
    //注册异常
    public static final int ERROR_REGISTER = 5003;
    //登陆异常
    private static final int ERROR_LOGIN = 5004;
    //账号有错误
    private static final int ERROR_ACCOUNT= 5005;

    //服务器整体错误异常
    private static final int ERROR_SERVER =6001;


    @Expose
    private int code;

    @Expose
    private String message;

    @Expose
    private M result;

    public ResponseModel(){
        code=1;
        message="ok";
    }

    public ResponseModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseModel(int code, String message, M result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <M> ResponseModel<M> buildOK(){
        return new ResponseModel<M>();
    }

    public static <M> ResponseModel<M> buildUnkownError(){
        return new ResponseModel<M>(ERROR_UNKNOW,"error_unknow");
    }

    public static <M> ResponseModel<M> buildParameterError(){
        return new ResponseModel<M>(ERROR_PARAMETERS,"error_parameters");
    }

    public static <M> ResponseModel<M> HavingUserError(){
        return new ResponseModel<M>(ERROR_HAVINGUSERERROR,"error_havinguser");
    }

    public static <M> ResponseModel<M> HavingPhoneError(){
        return new ResponseModel<M>(ERROR_HAVINGPHONE,"error_havingphone");
    }

    public static <M> ResponseModel<M> registerError(){
        return new ResponseModel<M>(ERROR_REGISTER,"error_register");
    }

    public static <M> ResponseModel<M> loginError(){
        return new ResponseModel<M>(ERROR_LOGIN,"error_login");
    }

    public static <M> ResponseModel<M> accountError(){
        return new ResponseModel<M>(ERROR_ACCOUNT,"error_account");
    }

    public static <M> ResponseModel<M> serverError(){
        return new ResponseModel<M>(ERROR_SERVER,"error_server");
    }

    public static <M> ResponseModel<M> buildOK(M result){
        return new ResponseModel<M>(SUCEED,"ok",result);
    }
}
