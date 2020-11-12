package ru.rambler.alexeimohov.dao.jpa.queries;

public interface UserQueries {
    String FIND_USER_BY_USERNAME_QUERY = "select u from User u where u.fullName =:name";
    String FIND_ALL_USERS_QUERY = "select u from User u";
}
