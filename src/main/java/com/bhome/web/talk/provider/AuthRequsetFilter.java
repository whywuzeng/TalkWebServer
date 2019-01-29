package com.bhome.web.talk.provider;

import com.bhome.web.talk.bean.api.base.ResponseModel;
import com.bhome.web.talk.bean.db.User;
import com.bhome.web.talk.factory.UserFactory;
import com.google.common.base.Strings;

import org.glassfish.jersey.server.ContainerRequest;

import java.io.IOException;
import java.security.Principal;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by Administrator on 2019-01-29.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.provider
 */

public class AuthRequsetFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String relationPath = ((ContainerRequest) requestContext).getPath(false);
        if (relationPath.startsWith("account/login") || relationPath.startsWith("account/register"))
        {
            return;
        }

        String token = ((ContainerRequest) requestContext).getHeaders().getFirst("token");
        if (!Strings.isNullOrEmpty(token))
        {
            //设置全局变量
           final User self = UserFactory.findByToken(token);
            if (self!=null)
            {
                requestContext.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return self;
                    }

                    @Override
                    public boolean isUserInRole(String role) {
                        return true;
                    }

                    @Override
                    public boolean isSecure() {
                        return false;
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return null;
                    }
                });

                return;
            }
        }

        ResponseModel<Object> model = ResponseModel.accountError();

        Response response = Response.status(Response.Status.OK)
                .entity(model)
                .build();

        requestContext.abortWith(response);
    }
}
