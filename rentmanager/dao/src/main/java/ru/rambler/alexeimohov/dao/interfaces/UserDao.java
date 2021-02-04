package ru.rambler.alexeimohov.dao.interfaces;

import ru.rambler.alexeimohov.entities.User;

public interface UserDao extends IGenericDao<User> {

    public User findByUserName(String userName);


}
