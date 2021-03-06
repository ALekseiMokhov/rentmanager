package ru.rambler.alexeimohov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rambler.alexeimohov.dto.MessageDto;
import ru.rambler.alexeimohov.dto.UserDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.UserMapper;
import ru.rambler.alexeimohov.service.interfaces.IMessageService;

import java.util.List;

/*
Controller @linked to  IMessageService
 * @method sendCustomMessage obtains @param ObjectNode from Jackson lib. It gathers UserDto and text supposed to send*/
@RestController
@RequestMapping("/messages")
public class MessageController {
    private IMessageService messageService;

    private UserMapper userMapper;

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    public MessageDto getById(@RequestParam long id) {
        return messageService.getById(id);
    }


    @GetMapping("/")
    public List<MessageDto> getAllMessages() {
        return messageService.getAll();
    }

    @PostMapping("/{topic}")
    public ResponseEntity sendCustomMessage(@PathVariable String topic, @RequestBody UserDto dto, @RequestBody String text) {
        messageService.sendCustomMessage(dto, topic, text);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
