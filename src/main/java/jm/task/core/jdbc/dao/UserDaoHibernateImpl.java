package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Session session;
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        session = Util.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS kata_db.users (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(45) NULL,\n" +
                "  lastname VARCHAR(45) NULL,\n" +
                "  age INT NULL,\n" +
                "  PRIMARY KEY (id))").executeUpdate();
        session.getTransaction().commit();
        System.out.println("Таблица создана.");
    }

    @Override
    public void dropUsersTable() {
        session = Util.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        session.getTransaction().commit();
        System.out.println("Таблица удалена.");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = Util.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        session = Util.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
        }
        session.getTransaction().commit();
        System.out.println("Пользователь с id = " + id + " удален.");
    }

    @Override
    public List<User> getAllUsers() {
        session = Util.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("FROM User").getResultList();
        session.getTransaction().commit();
        users.forEach(System.out::println);
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = Util.getInstance().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();
        System.out.println("Таблица очищена");
    }
}
