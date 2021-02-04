package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.UserDao;
import ru.rambler.alexeimohov.dao.jpa.queries.UserQueries;
import ru.rambler.alexeimohov.entities.User;

import java.util.List;

/* User DAO JPA implementation. Uses JPQL queries to sort data
 * @method findByUserName may retrieve User for authentication*/
@Repository(value = "userDao")
public class UserDaoJpaImpl extends GenericDaoJpa implements UserDao {


    public User findByUserName(String userName) {
        return (User) entityManager.createQuery(UserQueries.FIND_USER_BY_USERNAME_QUERY)
                .setParameter("name", userName)
                .getSingleResult()
                ;
    }

    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    public void remove(long id) {
        entityManager.remove(this.findById(id));
    }

    public void save(User object) {
        entityManager.persist(object);

    }

    public List<User> findAll() {
        return entityManager.createQuery(
                UserQueries.FIND_ALL_USERS_QUERY, User.class).getResultList();
    }

    @Override
    public void update(User object) {
        entityManager.merge(object);
    }
}
