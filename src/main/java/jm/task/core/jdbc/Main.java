package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService =  new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "Lyakun", (byte) 12);
        userService.saveUser("Matvei", "Krasnikov", (byte) 10);
        userService.saveUser("Dmitriy", "Gaifulin", (byte) 9);
        userService.saveUser("Varvara", "Videnko", (byte) 8);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
