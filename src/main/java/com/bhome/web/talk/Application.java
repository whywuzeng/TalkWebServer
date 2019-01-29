package com.bhome.web.talk;

import com.bhome.web.talk.provider.AuthRequsetFilter;
import com.bhome.web.talk.provider.GsonProvider;
import com.bhome.web.talk.service.AccountService;

import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;


/**
 * @author qiujuer
 */
public class Application extends ResourceConfig{
    public Application(){
        // 注册逻辑处理的包名
        //packages("net.qiujuer.web.italker.push.service");
        packages(AccountService.class.getPackage().getName());

        // 注册Json解析器
//        register(JacksonJsonProvider.class);

        register(GsonProvider.class);

        //要注册 拦截器
        register(AuthRequsetFilter.class);

        // 注册日志打印输出
        register(Logger.class);

    }
}
