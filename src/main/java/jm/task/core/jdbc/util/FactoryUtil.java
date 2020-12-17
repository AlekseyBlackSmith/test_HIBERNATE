package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class FactoryUtil {
    private static SessionFactory sessionFactory;

    private FactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение при создании sessionFactory: \n" + e);
            }
        }
        return sessionFactory;
    }
}
