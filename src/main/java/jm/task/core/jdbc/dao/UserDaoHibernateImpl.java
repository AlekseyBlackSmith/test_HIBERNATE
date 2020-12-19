package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {

        String createTable = "CREATE TABLE IF NOT EXISTS Users " +
                "(id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR (45) NOT NULL, " +
                "lastName VARCHAR (45) NOT NULL, " +
                "age INT NOT NULL, " +
                "PRIMARY KEY (id));";

        try (Session session = Util.getSession().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(createTable).executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана");

        } catch (Exception e) {
            System.out.println("Не удалось создать таблицу");
        }

    }

    @Override
    public void dropUsersTable() {

        String dropTable = "DROP TABLE IF EXISTS Users;";

        try (Session session = Util.getSession().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(dropTable).executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");

        } catch (Exception e) {
            System.out.println("Не удалось удалить таблицу");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (Session session = Util.getSession().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);

        } catch (Exception e) {
            System.out.printf("Не удалось добавить в базу пользователя с именем %s", name);
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = Util.getSession().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.printf("User c id %d удален из базы", id);

        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователяиз БД");
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        try (Session session = Util.getSession().openSession()) {

            Transaction transaction = session.beginTransaction();
            for (Object user : session.createQuery("FROM User").list()) {
                list.add((User) user);
            }
            transaction.commit();
            System.out.printf("Получен список из %d User's\n", list.size());

        } catch (Exception e) {
            System.out.println("Не удалось получить список User's");
        }

        return list;
    }

    @Override
    public void cleanUsersTable() {

        try (Session session = Util.getSession().openSession()) {

            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            System.out.println("Таблица очищенна");

        } catch (Exception e) {
            System.out.println("Не удалось очистить таблицу User's");
        }

    }
}