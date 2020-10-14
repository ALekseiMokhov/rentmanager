package com.senla.carservice.repository.jpa;

import com.senla.carservice.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJpaRepository extends GenericJpaRepository {

    public UserJpaRepository() {
        super.setClass(User.class);
    }

    public User getByName(String userName) {
        List<User> userList = findAll();
        return userList.stream()
                .filter(u -> u.getName() == userName)
                .findFirst()
                .get();
    }
}
