package ru.rambler.alexeimohov.dao.interfaces;

import ru.rambler.alexeimohov.entities.Subscription;
import ru.rambler.alexeimohov.entities.User;

public interface SubscriptionDao extends IGenericDao<Subscription> {
    User getSubscribeHolder(long id);

    boolean isExpired(long id);
}
