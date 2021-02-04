package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.MessageDao;
import ru.rambler.alexeimohov.dao.jpa.queries.MessageQueries;
import ru.rambler.alexeimohov.entities.Message;

import java.util.List;

/*
 * Message DAO JPA implementation. Uses JPQL queries to sort data*/
@Repository("messageDao")
public class MessageDaoImplJpa extends GenericDaoJpa implements MessageDao {


    @Override
    public Message findById(long id) {
        return entityManager.find(Message.class, id);

    }

    @Override
    public void remove(long id) {
        entityManager.remove(this.findById(id));
    }

    @Override
    public void save(Message object) {
        entityManager.persist(object);
    }

    @Override
    public List<Message> findAll() {
        return entityManager.createQuery(MessageQueries.FIND_ALL_MESSAGES)
                .getResultList();
    }

    @Override
    public void update(Message object) {
        entityManager.merge(object);
    }
}
