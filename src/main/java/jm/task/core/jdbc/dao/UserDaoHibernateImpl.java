package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.FactoryUtil;
import org.hibernate.Session;

import javax.transaction.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
/////////////////


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {
        Session session = 
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;

        try {
            session = FactoryUtil.getSessionFactory().openSession();
            Transaction tx = (Transaction) session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx.commit();
            System.out.printf("User с именем %s сохранен в БД\n", name);
        }catch (Exception e){
            System.out.printf("Сохранение в БД User'a с именем %s не удалось\n", name);

        } finally {
            session.close();
        }


    }

    @Override
    public void removeUserById(long id) {

        try{
            User user = (User) FactoryUtil.getSessionFactory().openSession().load(User.class, id);
            FactoryUtil.getSessionFactory().openSession().delete(user);
        } catch (Exception e) {
            System.out.printf("Не удалось удалить User'a с id %d\n", id);
        }

    }

    @Override
    public List<User> getAllUsers() {
        return  (List<User>)  FactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
    }

    @Override
    public void cleanUsersTable() {

    }
}
