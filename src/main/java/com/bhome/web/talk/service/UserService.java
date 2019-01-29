package com.bhome.web.talk.service;

import com.bhome.web.talk.bean.api.base.ResponseModel;
import com.bhome.web.talk.bean.api.user.UpdateInfoModel;
import com.bhome.web.talk.bean.card.UserCard;
import com.bhome.web.talk.bean.db.User;
import com.bhome.web.talk.factory.UserFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Administrator on 2019-01-29.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.service
 */
@Path("/user")
public class UserService extends BaseService{

    // api/user
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> update(UpdateInfoModel model){
        if (!UpdateInfoModel.check(model))
        {
            return ResponseModel.buildParameterError();
        }

        User self = getSelf();
        User user = model.updataInfo(self);
        user= UserFactory.updata(user);
        UserCard userCard = new UserCard(user);
        return ResponseModel.buildOK(userCard);
    }
}
