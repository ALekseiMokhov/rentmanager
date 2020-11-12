package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.AddressDao;
import ru.rambler.alexeimohov.dao.jpa.queries.AddressQueries;
import ru.rambler.alexeimohov.entities.Address;

import java.util.List;

@Repository("addressDao")
public class AddressDaoJpaImpl extends GenericJpaDao implements AddressDao {

    @Override
    public Address findById(Long id) {
        return entityManager.find( Address.class, id );
    }

    @Override
    public void remove(Long id) {
        entityManager.remove( this.findById( id ) );
    }

    @Override
    public void save(Address object) {
        entityManager.persist( object );
    }

    @Override
    public List <Address> findAll() {
        return entityManager.createQuery( AddressQueries.FIND_ALL_ADDRESSES ).getResultList();
    }

    @Override
    public void update(Address object) {
        entityManager.merge( object );
    }
}
