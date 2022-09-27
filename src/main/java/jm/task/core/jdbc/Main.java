package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService service = new UserServiceImpl();
        UserDaoHibernateImpl daoHibernate = new UserDaoHibernateImpl();

        service.createUsersTable();

//        service.saveUser("Anton", "Lebedev", (byte) 25);
//        service.saveUser("Ivan", "Antonov", (byte) 30);
//        service.saveUser("Tom", "Cruz", (byte) 55);
//        service.saveUser("Lee", "Shady", (byte) 26);
//
//        service.getAllUsers();
//
//        service.cleanUsersTable();
//
//        service.dropUsersTable();
    }
}
