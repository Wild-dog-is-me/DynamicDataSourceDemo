package org.dog.server;

import org.dog.server.mapper.UserMapper;
import org.dog.server.model.User;
import org.dog.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DynamicDataSourceDemoApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper um;

    @Test
    void contextLoads() {
        Object allUsers = userService.getAllUsers();
//        System.out.println(allUsers);
        System.out.println(allUsers);
//        for (User allUser : allUsers) {
//            System.out.println(allUser);
//        }

    }

}
