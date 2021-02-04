package ru.rambler.alexeimohov.dao.jpa.queries;

public interface SubscriptionQueries {
    String FIND_ALL_SUBSCRIPTION = "select s from Subscription s";

    String FIND_USER_FOR_SUBSCRIPTION =
            "select * from subscription s inner join user_subscription u_s on s.id = u_s.id_subscription" +
                    "inner join user u on u.id =  u_s.id_user" +
                    " where s.id = ? ";


}
