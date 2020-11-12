package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.MessageDao;
import ru.rambler.alexeimohov.dao.jpa.queries.MessageQueries;
import ru.rambler.alexeimohov.entities.Message;

import java.util.List;

@Repository("messageDao")
public class MessageJpaDaoImpl extends GenericJpaDao implements MessageDao {


    @Override
    public Message findById(Long id) {
        return entityManager.find( Message.class, id );

    }

    @Override
    public void remove(Long id) {
        entityManager.remove( this.findById( id ) );
    }

    @Override
    public void save(Message object) {
        entityManager.persist( object );
    }

    @Override
    public List <Message> findAll() {
        return entityManager.createNativeQuery( MessageQueries.FIND_ALL_MESSAGES ).getResultList();
    }

    @Override
    public void update(Message object) {
        entityManager.merge( object );
    }
}
