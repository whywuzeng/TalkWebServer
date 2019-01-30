package com.bhome.web.talk.factory;

import com.bhome.web.talk.Utils.Hib;
import com.bhome.web.talk.Utils.TextUtil;
import com.bhome.web.talk.bean.db.User;
import com.bhome.web.talk.bean.db.UserFollow;
import com.google.common.base.Strings;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Administrator on 2019-01-28.
 *
 * by author wz
 *
 * com.bhome.web.talk.factory
 *
 */

public class UserFactory {

    public static User findByToken(String token)
    {
      return Hib.query(session ->
                (User) session.createQuery
                        ("from User where token =:token")
                .setParameter("token",token)
                .uniqueResult()
        );
    }

    /**
     * 根据ID查询
     */
    public static User findByUserId(String userId)
    {
        return Hib.query(session ->
                (User) session.createQuery
                        ("from User where id =:userId")
                        .setParameter("userId",userId)
                        .uniqueResult()
        );
    }

    /**
     * 查找name是否存在
     * @param name
     * @return
     */
    public static User findByName(String name)
    {
       return  Hib.query(session ->
           (User)session.createQuery("from User where name=:name")
                   .setParameter("name",name)
                   .uniqueResult()
        );
    }

    /**
     * 查找phone 是否存在
     * @param phone
     * @return
     */
    public static User findByPhone(String phone)
    {
        return Hib.query(session ->
            (User)session.createQuery("from User where phone=:phone")
                  .setParameter("phone",phone)
                  .uniqueResult()
        );
    }

    /**
     * 注册一个账号
     * @param account
     * @param phone
     * @param name
     * @return
     */
    public static User register(String account,String phone,String name) {
        // 去除账户中首位空格
        name = name.trim();
        account = encodePassword(account);
        User user = createUser(account, phone, name);
        if (user!=null)
        {
            user= upDataToken(user);
        }
        return user;
    }

    /**
     * 登陆判断
     */
    public static User login(String name,String account)
    {
        String encodePassword = encodePassword(account);

        User user = Hib.query(session ->
                (User) session.createQuery("from User where phone=:phone and account=:account")
                        .setParameter("phone", name)
                        .setParameter("account", encodePassword)
                        .uniqueResult()
        );

        if (user!=null)
        {
            user= upDataToken(user);
        }
        return user;
    }

    public static User bind(User self,String pushId)
    {
        Hib.queryOnly(session -> {
            List<User> list = (List<User>)
                    session.createQuery("from User where pushId=:pushId and id!=:userId")
                    .setParameter("pushId", pushId)
                    .setParameter("userId", self.getId())
                    .list();
            for (User item : list) {
                item.setPushId(null);
                session.saveOrUpdate(item);
            }
        });

        if (self.getPushId().equalsIgnoreCase(pushId))
        {
            return self;
        }

        if (Strings.isNullOrEmpty(self.getPushId()))
        {
            //Todo 退出消息
        }

        self.setPushId(pushId);
        return upDataToken(self);
    }

    /**
     * 创建一个User
     * @param account
     * @param phone
     * @param name
     * @return
     */
    private static User createUser(String account,String phone,String name)
    {
        User user = new User();
        user.setAccount(account);
        user.setName(name);
        user.setPhone(phone);

        Hib.query(session -> {
            session.save(user);
            return user;
        });
        return user;
    }

    private static String encodePassword(String account) {
        account = account.trim();
        account = TextUtil.getMD5(account);
        return TextUtil.encodeBase64(account);
    }

    /**
     * 设置token
     * @param user
     * @return
     */
    public static User upDataToken(User user)
    {
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        updata(user);
        return user;
    }

    /**
     * 更新User
     * @param user
     */
    public static User updata(User user) {
       return Hib.query(session -> {
            session.saveOrUpdate(user);
            return user;
        });
    }

    /**
     * 获取关注人列表
     */
    public static Set<UserFollow> contact(User user)
    {
        return Hib.query(session -> {
           session.load(user,user.getId());

            return user.getFollowing();
        });
    }

    /**
     * 关注人
     * @param self 自己
     * @param target 别人
     * @param alias 备注名
     */
    public static User follow(User self, User target, String alias) {
        UserFollow follow = UserFactory.isFollow(self, target);
        if (follow != null)
        {
            return follow.getFollower();
        }

       return Hib.query(session -> {
           session.load(self,self.getId());
           session.load(target,target.getId());

            UserFollow selfFollow = new UserFollow();
            selfFollow.setOrigin(self);
            selfFollow.setFollower(target);
            selfFollow.setAlies(alias);

            UserFollow targetFollow = new UserFollow();
            targetFollow.setOrigin(target);
            targetFollow.setFollower(self);
            targetFollow.setAlies(alias);

            session.save(selfFollow);
            session.save(targetFollow);

            return target;
        });
    }

    /**
     * 查询是否已经关注
     * @param self
     * @param target
     */
    public static UserFollow isFollow(User self, User target) {
       return Hib.query(session -> (UserFollow) session.createQuery
               ("from UserFollow where originId=:originId and followerId =:followerId")
               .setParameter("originId",self.getId())
               .setParameter("followerId",target.getId())
               .uniqueResult());

    }

    /**
     * 查询name
     * @param name
     */
    public static List<User> search(String name) {
        if (Strings.isNullOrEmpty(name))
        {
            name="";
        }
         final String searchName = "%"+name+"%";
       return  Hib.query(session -> {
           return (List<User>) session.createQuery("from User where lower(name) like:name and portrait is not null and description is not null ")
                    .setParameter("name",searchName)
                   .setMaxResults(20)
                    .list();
        });
    }
}
