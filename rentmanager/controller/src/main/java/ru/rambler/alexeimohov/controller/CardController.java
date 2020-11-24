package ru.rambler.alexeimohov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.CardDto;
import ru.rambler.alexeimohov.service.interfaces.ICardService;

@Controller
@RequestMapping("/card")
public class CardController {

    private ICardService cardService;

    public CardController(ICardService cardService) {
        this.cardService = cardService;
    }
      @PostMapping("/")
    public ResponseEntity saveCard(@RequestBody  CardDto dto){
        cardService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.CREATED );
    }
    @GetMapping("/{id}")
    public CardDto getById(@PathVariable long id){
         return cardService.getById( id ) ;
    }

    @GetMapping("/{cardNumber}")
    public CardDto getByCardNumber(@PathVariable long cardNumber){
        return cardService.getByCardNumber( cardNumber );
    }
     @PutMapping("/")
    public ResponseEntity updateAddress(@RequestBody  CardDto dto){
        cardService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.OK );

    }
}
