package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.CardDao;
import ru.rambler.alexeimohov.dao.jpa.queries.CardQueries;
import ru.rambler.alexeimohov.entities.Card;

import java.util.List;
    @Repository
public class CardDaoJpaImpl extends GenericJpaDao implements CardDao {
    @Override
    public Card findByCardNumber(Long number) {
        return (Card) entityManager.createQuery( CardQueries.FIND_CARD_BY_NUMBER )
                .setParameter( "number", number )
                .getSingleResult();
    }

    @Override
    public Card findById(Long id) {
        return entityManager.find( Card.class, id );
    }

    @Override
    public void remove(Long id) {
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
