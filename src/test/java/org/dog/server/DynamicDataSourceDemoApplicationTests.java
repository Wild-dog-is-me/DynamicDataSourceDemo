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
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> allUsers = userService.getAllUsers();
//        for (User allUser : allUsers) {
//            System.out.println(allUser);
//        }
        System.out.println(allUsers);
        System.out.println();
        userMapper.getAllUsers().forEach(System.out::println);

    }

}
