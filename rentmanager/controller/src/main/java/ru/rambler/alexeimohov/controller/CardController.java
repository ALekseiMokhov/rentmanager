package ru.rambler.alexeimohov.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.service.interfaces.ICardService;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private ICardService cardService;

    public CardController(ICardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{id}")
    public CardDto getById(@PathVariable long id) {
        return cardService.getById( id );
    }

    @GetMapping("/{cardNumber}")
    public CardDto getByCardNumber(@PathVariable long cardNumber) {
        return cardService.getByCardNumber( cardNumber );
    }

    public List <CardDto> getByUserName(@PathVariable String userName) {
        return cardService.getByUserName( userName );
    }

    @GetMapping("/")
    public List <CardDto> getAll() {
        return cardService.getAll();
    }
}
