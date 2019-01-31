package com.bhome.web.talk.bean.api.base;

import com.bhome.web.talk.Utils.TextUtil;
import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019-01-30.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.bean.api.base
 */

public class PushModel {

    private List<Entity> entities =new ArrayList<>();

    public PushModel add(String entity,int type){
        Entity objentity = new Entity(entity, type);
        entities.add(objentity);
        return this;
    }

    public PushModel add(Entity entity)
    {
        entities.add(entity);
        return this;
    }

    public String getEntityString(){
        if (entities.size()>0)
        {
        return TextUtil.toJson(entities);
        }else
        {
            return null;
        }
    }

    public static class Entity{
        public Entity(String entity, int type) {
            this.context = entity;
            this.type = type;
        }


        @Expose
        public int type;

        @Expose
        public String context;

        @Expose
        public LocalDateTime createAt= LocalDateTime.now();
    }
}
