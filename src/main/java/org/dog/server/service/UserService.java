package org.dog.server.service;

import lombok.extern.slf4j.Slf4j;
import org.dog.server.annotation.DataSource;
import org.dog.server.datasource.DataSourceType;
import org.dog.server.mapper.UserMapper;
import org.dog.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Odin
 * @Date: 2023/6/2 10:09
 * @Description:
 */

@Slf4j
@Service
@DataSource("slave")
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        log.error("mapper-data:{}", userMapper.getAllUsers());
        userMapper.getAllUsers().forEach(System.out::println);
        userMapper.update();
        return userMapper.getAllUsers();
    }
}
