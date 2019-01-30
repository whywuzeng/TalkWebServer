package com.bhome.web.talk.service;

import com.bhome.web.talk.bean.api.base.ResponseModel;
import com.bhome.web.talk.bean.api.user.UpdateInfoModel;
import com.bhome.web.talk.bean.card.UserCard;
import com.bhome.web.talk.bean.db.User;
import com.bhome.web.talk.bean.db.UserFollow;
import com.bhome.web.talk.factory.UserFactory;
import com.google.common.base.Strings;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
public class UserService extends BaseService {

    // api/user
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> update(UpdateInfoModel model) {
        if (!UpdateInfoModel.check(model)) {
            return ResponseModel.buildParameterError();
        }

        User self = getSelf();
        User user = model.updataInfo(self);
        user = UserFactory.updata(user);
        UserCard userCard = new UserCard(user);
        return ResponseModel.buildOK(userCard);
    }

    /**
     * 获取我的联系人
     */
    @GET
    @Path("/contact")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<List<UserCard>> contact() {
        User self = getSelf();
        Set<UserFollow> followings = UserFactory.contact(self);

        List<User> users = followings.stream().map(new Function<UserFollow, User>() {
            @Override
            public User apply(UserFollow userFollow) {

                return userFollow.getFollower();
            }
        }).collect(Collectors.toList());

        List<UserCard> usercards = users.stream().map(new Function<User, UserCard>() {
            @Override
            public UserCard apply(User user) {

                return new UserCard(user);
            }
        }).collect(Collectors.toList());

        return ResponseModel.buildOK(usercards);
    }

    /**
     * 关注人
     * //简化  关注了别人 同时别人也关注了我
     */
    @PUT
    @Path("/flower/{followId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseModel<UserCard> follow(@PathParam("followId") String followId){
        if (!Strings.isNullOrEmpty(followId))
        {
            return ResponseModel.buildParameterError();
        }

        User follower = UserFactory.findByUserId(followId);
        if (follower!=null)
        {
            return ResponseModel.buildParameterError();
        }

        User self = getSelf();

        if (self.getId().equalsIgnoreCase(follower.getId()))
        {
            return ResponseModel.followError();
        }

        User target = UserFactory.follow(self, follower, null);

        if (target==null)
        {
            return ResponseModel.serverError();
        }
        UserCard userCard = new UserCard(target);

        return ResponseModel.buildOK(userCard);
    }

    /**
     * 获取某人的信息
     */
     @GET
     @Path("/userinfo/{userId}")
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     public ResponseModel<UserCard> userInfo(@PathParam("userId") String userId){
         if (Strings.isNullOrEmpty(userId))
         {
             return ResponseModel.buildParameterError();
         }

         User self = getSelf();
         if (self.getId().equalsIgnoreCase(userId))
         {
             return ResponseModel.buildOK(new UserCard(self,true));
         }

         User user = UserFactory.findByUserId(userId);
         if (user==null)
         {
             return ResponseModel.noFoundError();
         }

         boolean isFollow= UserFactory.isFollow(self, user) != null;

         UserCard userCard = new UserCard(user,isFollow);
         return ResponseModel.buildOK(userCard);
     }

    /**
     * 搜索人的接口实现
     */
    @GET
    @Path("/search/{name:(.*)?}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<List<UserCard>> search(@DefaultValue("") @PathParam("name") String name){
        User self = getSelf();

        List<User> searchUsers = UserFactory.search(name);

        Set<UserFollow> contacts = UserFactory.contact(self);

        List<UserCard> userCards = searchUsers.stream().map(new Function<User, UserCard>() {
            @Override
            public UserCard apply(User user) {
                boolean isFollow = user.getId().equalsIgnoreCase(self.getId())
                        || contacts.stream().anyMatch(new Predicate<UserFollow>() {
                    @Override
                    public boolean test(UserFollow userFollow) {

                        return user.getId().equalsIgnoreCase(userFollow.getOriginId());
                    }
                });

                return new UserCard(user, isFollow);
            }
        }).collect(Collectors.toList());

        return ResponseModel.buildOK(userCards);
    }
}
