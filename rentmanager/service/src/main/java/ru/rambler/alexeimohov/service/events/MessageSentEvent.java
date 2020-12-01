package ru.rambler.alexeimohov.service.events;

import ru.rambler.alexeimohov.dto.MessageDto;

public class MessageSentEvent {

    private MessageDto messageDto;

    public MessageSentEvent(MessageDto messageDto) {
        this.messageDto = messageDto;
    }
}
