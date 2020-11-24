package ru.rambler.alexeimohov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/")
    public ResponseEntity saveCard(@Validated @RequestBody CardDto dto) {
        cardService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    public CardDto getById(@PathVariable long id) {
        return cardService.getById( id );
    }

    @GetMapping("/{cardNumber}")
    public CardDto getByCardNumber(@PathVariable long cardNumber) {
        return cardService.getByCardNumber( cardNumber );
    }

    @PutMapping("/")
    public ResponseEntity updateAddress(@Validated @RequestBody CardDto dto) {
        cardService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.OK );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCard(@PathVariable long id) {
        cardService.remove( id );
        return new ResponseEntity( HttpStatus.OK );
    }

    @GetMapping("/")
    public List <CardDto> getAll() {
        return cardService.getAll();
    }
}
