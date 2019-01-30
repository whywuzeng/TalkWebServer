package com.bhome.web.talk.factory;

import com.bhome.web.talk.Utils.Hib;
import com.bhome.web.talk.bean.api.message.MessageModel;
import com.bhome.web.talk.bean.db.Message;
import com.bhome.web.talk.bean.db.User;

/**
 * Created by Administrator on 2019-01-30.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.factory
 */

public class MessageFactory {

    public static Message findById(String id){

      return Hib.query(session -> {
            return (Message) session.createQuery("from Message where id = :id")
                    .setParameter("id",id)
                    .uniqueResult();
        });
    }

    public static Message add(User self, User receiver, MessageModel model) {
        Message message = new Message(self, receiver, model);
        return Hib.query(session -> {
           return (Message)session.save(message);
        });
    }
}
