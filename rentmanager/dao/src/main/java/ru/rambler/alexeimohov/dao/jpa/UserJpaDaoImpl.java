package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.UserDao;
import ru.rambler.alexeimohov.dao.jpa.queries.UserQueries;
import ru.rambler.alexeimohov.entities.User;

import java.util.List;

@Repository(value = "userDao")
public class UserJpaDaoImpl extends GenericJpaDao implements UserDao {


    public User findByUserName(String userName) {
        return (User) entityManager.createQuery( UserQueries.FIND_USER_BY_USERNAME_QUERY )
                .setParameter( "name", userName )
                .getSingleResult()
                ;
    }

    public User findById(Long id) {
        return entityManager.find( User.class, id );
    }

    public void remove(Long id) {
        entityManager.remove( this.findById( id ) );
    }

    public void save(User object) {
        entityManager.persist( object );

    }

    public List <User> findAll() {
        return entityManager.createQuery(
                UserQueries.FIND_ALL_USERS_QUERY, User.class ).getResultList();
    }

    @Override
    public void update(User object) {
        entityManager.merge( object );
    }
}
