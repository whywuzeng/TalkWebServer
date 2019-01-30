package com.bhome.web.talk.factory;

import com.bhome.web.talk.Utils.Hib;
import com.bhome.web.talk.Utils.PushDispatcher;
import com.bhome.web.talk.Utils.TextUtil;
import com.bhome.web.talk.bean.card.MessageCard;
import com.bhome.web.talk.bean.db.Message;
import com.bhome.web.talk.bean.db.PushHistory;
import com.bhome.web.talk.bean.db.User;
import com.google.common.base.Strings;

/**
 * Created by Administrator on 2019-01-30.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.factory
 */

public class PushFactory {

    public static void pushMessage(User sender,Message message)
    {
        if (sender==null || message == null)
            return;

        PushDispatcher dispatcher = new PushDispatcher();

        if (message.getGroup()==null&&Strings.isNullOrEmpty(message.getGroupId())) {
            MessageCard messageCard = new MessageCard(message);

            String entity = TextUtil.toJson(messageCard);

            User recevier = UserFactory.findByUserId(message.getReceiverId());

            if (recevier == null)
                return;

            PushHistory history = new PushHistory();
            history.setEntity(entity);
            history.setEntityType(messageCard.getType());
            history.setReceiver(recevier);
            history.setReceiverPushId(recevier.getPushId());

            Hib.query(session ->
               session.save(history)
            );


        }else {
            //群消息
        }

    }
}
