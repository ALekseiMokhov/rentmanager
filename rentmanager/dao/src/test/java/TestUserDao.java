import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.MessageDao;
import ru.rambler.alexeimohov.dao.interfaces.SubscriptionDao;
import ru.rambler.alexeimohov.dao.interfaces.UserDao;
import ru.rambler.alexeimohov.entities.Message;
import ru.rambler.alexeimohov.entities.Subscription;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.entities.enums.Privilege;
import ru.rambler.alexeimohov.entities.enums.Role;

import java.time.LocalDate;
import java.util.List;

@ContextConfiguration(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
public class TestUserDao {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private SubscriptionDao subscriptionDao;

    private Message message;
    private User user;
    private Subscription subscription;

    @BeforeEach
    @Transactional
    void init() {
        message = new Message();
        message.setText( "Welcome to our site, Evgeny!" );

        subscription = new Subscription();
        subscription.setStartDate( LocalDate.of( 2020, 01, 01 ) );
        subscription.setExpirationDate( LocalDate.of( 2020, 02, 01 ) );
        subscription.setPrice( 20.5 );

        user = new User();
        user.setFullName( "Evgeny Ivanov" );
        user.setEmail( "coder@gmail.com" );
        user.setPassword( "4fwfm2n8qlb" );
        user.setPhoneNumber( 8_999_306_22_22l );
        user.setRole( Role.USER );
        user.setPrivilege( Privilege.NEWBIE );
        user.setSubscription( subscription );
        user.addMessage( message );
        userDao.save( user );
    }

    @Test
    @Transactional
    @Rollback
    void persistAndExpectEqualObjects() {

        User retrieved = userDao.findById( 1l );
        Message message = messageDao.findById( retrieved.getId() );
        List <Message> messages = messageDao.findAll();

        Assertions.assertEquals( "Evgeny Ivanov", retrieved.getFullName() );
        Assertions.assertEquals( "Welcome to our site, Evgeny!", message.getText() );
        Assertions.assertEquals( 1, messages.size() );
    }

    @Test
    @Transactional
    @Rollback
    void deleteMessageAndExpectConsistency() {
        Message toDelete = userDao.findById( 1l ).getMessages().get( 0 );
        Assertions.assertNotNull( toDelete );
        User retrieved = userDao.findById( 1l );
        retrieved.removeMessage( toDelete );
        userDao.update( retrieved );

        Assertions.assertNull( messageDao.findById( 1l ).getUser() );

    }

    @Test
    @Transactional
    @Rollback
    void deleteSubscriptionAndExpectConsistency() {
        User userRetrieved = userDao.findById( 1l );
        Subscription retrievedSub = userRetrieved.getSubscription();

        Assertions.assertNotNull( retrievedSub );

        userRetrieved.setSubscription( null );
        userDao.update( userRetrieved );

        Assertions.assertEquals( 0, subscriptionDao.findAll().size() );

    }

}
