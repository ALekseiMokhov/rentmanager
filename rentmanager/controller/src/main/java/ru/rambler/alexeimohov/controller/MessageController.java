package ru.rambler.alexeimohov.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.service.interfaces.IMessageService;

import java.util.List;

/*
 * method sendCustomMessage obtains @param ObjectNode from Jackson lib. It gathers UserDto and Text supposed to send*/
@RestController
@RequestMapping("/message")
public class MessageController {
    private IMessageService messageService;

    private UserMapper userMapper;

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/")
    public ResponseEntity createMessage(@Validated @RequestBody MessageDto dto) {
        messageService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.CREATED );
    }

    @GetMapping("/{id}")
    public MessageDto getById(@RequestParam long id) {
        return messageService.getById( id );
    }

    @PutMapping("/")
    public ResponseEntity updateMessage(@Validated @RequestBody MessageDto dto) {
        messageService.saveOrUpdate( dto );
        return new ResponseEntity( HttpStatus.OK );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMessage(@RequestParam long id) {

        messageService.remove( id );
        return new ResponseEntity( HttpStatus.OK );

    }

    @GetMapping("/")
    public List <MessageDto> getAllMessages() {
        return messageService.getAll();
    }

    @PostMapping("/{topic}")
    public ResponseEntity sendCustomMessage(@PathVariable String topic, @RequestBody ObjectNode objectNode) {
        messageService.sendCustomMessage( /*objectNode.get( "dto" ).*/null, topic, objectNode.get( "text" ).asText() );

        return new ResponseEntity( HttpStatus.CREATED );
    }
}
