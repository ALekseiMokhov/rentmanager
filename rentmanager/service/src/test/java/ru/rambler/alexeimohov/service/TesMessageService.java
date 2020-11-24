package ru.rambler.alexeimohov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rambler.alexeimohov.dao.interfaces.MessageDao;
import ru.rambler.alexeimohov.dao.jpa.MessageDaoJpaImpl;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.MessageMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.MessageMapperImpl;
import ru.rambler.alexeimohov.entities.Message;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.*;


/*
 * Unit tests with BDD mockito and classic mockito methods*/
@ExtendWith(MockitoExtension.class)
class TesMessageService {

    private MessageDao messageDao = Mockito.mock( MessageDaoJpaImpl.class );

    private MessageMapper mapper = Mockito.mock( MessageMapperImpl.class );

    @InjectMocks
    private MessageService service;

    private Message message;

    private MessageDto messageDto;

    @BeforeEach
    void init() {
        this.message = new Message( 1l, "Text", null, LocalDateTime.MIN );
        this.messageDto = new MessageDto( "1", "Text", null, LocalDateTime.MIN.toString() );
    }

    @Test
    void saveOrUpdateAndExpectCorrectMethod() {
        //given
        given( messageDao.findById( anyLong() ) ).willReturn( null );
        when( mapper.fromDto( any() ) ).thenReturn( message );
        //when
        service.saveOrUpdate( this.messageDto );
        //then
        then( messageDao ).should( never() )
                .update( message );
        verify( messageDao, times( 1 ) ).save( message );
    }

}
