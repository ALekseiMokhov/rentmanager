package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.CardDao;
import ru.rambler.alexeimohov.dao.jpa.queries.CardQueries;
import ru.rambler.alexeimohov.entities.Card;

import java.util.List;

@Repository
public class CardDaoImplJpa extends GenericDaoJpa implements CardDao {
    @Override
    public Card findByCardNumber(long number) {
        return (Card) entityManager.createQuery( CardQueries.FIND_CARD_BY_NUMBER )
                .setParameter( "number", number )
                .getSingleResult();
    }

    @Override
    public Card findById(long id) {
        return entityManager.find( Card.class, id );
    }

    @Override
    public void remove(long id) {
        entityManager.remove( this.findById( id ) );
    }

    @Override
    public void save(Card object) {
        entityManager.persist( object );
    }

    @Override
    public List <Card> findAll() {
        return entityManager.createQuery( CardQueries.FIND_ALL_CARDS )
                .getResultList();
    }

    @Override
    public void update(Card object) {
        entityManager.merge( object );
    }
}
