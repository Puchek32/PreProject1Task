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
        try (Connection conn = Util.getConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS mydb.User" +
                    "(id BIGINT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(255), " +
                    "lastName VARCHAR(255), " +
                    "age TINYINT," +
                    "PRIMARY KEY (id));";
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection()) {
            String sql = "DROP TABLE IF EXISTS mydb.User";
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnection()) {
            PreparedStatement pst =
                    conn.prepareStatement("insert into user (name, lastname, age) values (?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setByte(3, age);
            pst.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection()) {
            String sql = "Delete from user where id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Connection conn = Util.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT name, lastName, age FROM User;")) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getByte("age"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection()) {
            String sql = "TRUNCATE mydb.User";
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
