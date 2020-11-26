package ru.rambler.alexeimohov.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rambler.alexeimohov.dao.interfaces.UserDao;
import ru.rambler.alexeimohov.dao.jpa.UserDaoJpaImpl;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.*;
import ru.rambler.alexeimohov.entities.Card;
import ru.rambler.alexeimohov.entities.Message;
import ru.rambler.alexeimohov.entities.User;
import ru.rambler.alexeimohov.service.interfaces.IUserService;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class TestUserService {


    private UserDao userDao = Mockito.mock( UserDaoJpaImpl.class ) ;

    private UserMapper userMapper = Mockito.mock( UserMapperImpl.class );

    private CardMapper cardMapper = Mockito.mock( CardMapperImpl.class );

    private MessageMapper messageMapper = Mockito.mock( MessageMapperImpl.class );

    private User user;

    private Card card;

    private Message message;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init(){
        this.user = new User();
        this.card = new Card();
        this.message = new Message();
    }

    @Test
    void addCreditCardANdExpectConsistency() {
        //given
        given(userDao.findById( anyLong() )).willReturn( user);
        given( cardMapper.fromDto( any(CardDto.class)  ) ).willReturn(card) ;
        //when
        userService.addCreditCard( 666l,new CardDto() );
        //then
        verify( userDao,times( 1 ) ) .findById( 666l );
        verify( cardMapper,times( 1 ) ) .fromDto( any(CardDto.class) );
        Assertions.assertEquals(user.getCreditCards().get( 0 ) , card );
    }

    @Test
    void removeCreditCardAndExpectNullUser() {
        //given
        given(userDao.findById( anyLong() )).willReturn( user);
        given( cardMapper.fromDto( any(CardDto.class)  ) ).willReturn(card) ;
        card.setUser( user );
        //when
        userService.removeCreditCard( anyLong(),new CardDto() );
        //then
        verify( cardMapper,times( 1 ) ) .fromDto( any(CardDto.class ) ) ;
        Assertions.assertNull( card.getUser() );
    }

    @Test
    void addMessageAndExpectConsistency() {
        //given
        given(userDao.findById( anyLong() )).willReturn( user);
        given( messageMapper.fromDto( any( MessageDto.class)  ) ).willReturn(message) ;
        //when
        userService.addMessage( 25l,new MessageDto() );
        //then
        verify( userDao,times( 1 ) ) .findById( 25l );
        verify( messageMapper,times( 1 ) ) .fromDto( any(MessageDto.class) );
        Assertions.assertEquals(user.getMessages().get( 0 ) , message );
    }

    @Test
    void removeMessageAndExpectNullUser() {
        //given
        given(userDao.findById( anyLong() )).willReturn( user);
        given( messageMapper.fromDto( any(MessageDto.class)  ) ).willReturn(message) ;
        message.setUser( user );
        //when
        userService.removeMessage( anyLong(),new MessageDto() );
        //then
        verify( messageMapper,times( 1 ) ) .fromDto( any(MessageDto.class ) ) ;
        Assertions.assertNull( card.getUser() );
    }


}
