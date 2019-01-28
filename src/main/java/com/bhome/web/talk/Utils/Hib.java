package com.bhome.web.talk.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by Administrator on 2019-01-28.
 * <p>
 * by author wz
 * <p>
 * com.bhome.web.talk.Utils
 */

public class Hib {

    //全局sessionFactory
    private static SessionFactory factory;

    static {
        init();
    }

    private static void init() {
        //init()  从hibernate。cfg。xml 文件初始化参数
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        //要捕获异常然后，打印log
        //build 一个sessionFactory
        try {
            factory= new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            //打印log
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }

    //获取全局的sessionFactory
    public static SessionFactory getFactory() {
        return factory;
    }

    //得到当前session回话
    public static Session getSession(){
        return factory.getCurrentSession();
    }

    //关闭 sessionFactory
    public void closeFactory(){
        if (factory!=null)
        {
            factory.close();
        }
    }


    public interface QueryOnly{
        void query(Session session);
    }

    public static void queryOnly(QueryOnly queryOnly)
    {
        //得到seesion 操作事物  。关闭操作，异常捕获
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {

            queryOnly.query(session);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();

            try {
                transaction.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }finally {
            session.close();
        }
    }

    public interface Query<T>{
        T query(Session session);
    }

    public static <T> T query(Query<T> tQuery)
    {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        T t =null;
        try {
             t = tQuery.query(session);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (RuntimeException e1) {
                e1.printStackTrace();
            }
        }finally {
            session.close();
        }
        return t;
    }
}
