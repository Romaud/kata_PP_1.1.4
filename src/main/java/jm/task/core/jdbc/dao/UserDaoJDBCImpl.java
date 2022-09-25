package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS kata_db.users (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(45) NULL,\n" +
                "  lastname VARCHAR(45) NULL,\n" +
                "  age INT NULL,\n" +
                "  PRIMARY KEY (id))";
        try (Statement statement = Util.getInstance().getConnection().createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица создана.");
        } catch (SQLException e) {
            System.err.println("Ошибка при создании таблицы.");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Statement statement = Util.getInstance().getConnection().createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица удалена.");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении таблицы.");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Util.getInstance().getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении пользователя.");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = Util.getInstance().getConnection().prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("Пользователь с id = " + id + " удален.");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении пользователя.");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Statement statement = Util.getInstance().getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении данных из таблицы.");
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Statement statement = Util.getInstance().getConnection().createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении данных из таблицы.");
            e.printStackTrace();
        }
    }
}
