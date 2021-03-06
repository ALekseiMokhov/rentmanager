package ru.rambler.alexeimohov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rambler.alexeimohov.dao.interfaces.CardDao;
import ru.rambler.alexeimohov.dao.jpa.CardDaoImplJpa;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.CardMapper;
import ru.rambler.alexeimohov.dto.mappers.interfaces.CardMapperImpl;
import ru.rambler.alexeimohov.entities.Card;
import ru.rambler.alexeimohov.entities.User;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/*
 * Unit tests with BDD mockito and classic mockito methods*/
@ExtendWith(MockitoExtension.class)
class TestCardService {

    private CardDao cardDao = Mockito.mock(CardDaoImplJpa.class);

    private CardMapper mapper = Mockito.mock(CardMapperImpl.class);

    @InjectMocks
    private CardService service;

    private Card card;

    private CardDto cardDto;

    private User user;

    @BeforeEach
    void init() {
        this.user = new User();
        this.user.setUsername("Sergei Ivanov");
        this.user.setId(1l);
        this.card = new Card(1l, LocalDate.of(2040, 12, 31),
                LocalDate.now(), 4556140832208361l, 1000.0, 50.0, user);
        this.cardDto = new CardDto("1", "2040-12-31", "2020-11-12T09:00:00",
                "4556140832208361", "1000", "300", "1");
    }


    @Test
    void getByCardNumberAndExpectMaptoDto() {
        //given
        given(cardDao.findByCardNumber(4556140832208361l)).willReturn(card);
        //when
        service.getByCardNumber(4556140832208361l);
        //then
        then(mapper).should().toDto(card);
    }


}
