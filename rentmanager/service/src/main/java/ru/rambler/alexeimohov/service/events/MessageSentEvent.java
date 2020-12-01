package ru.rambler.alexeimohov.service.events;

import lombok.Getter;
import lombok.Setter;
import ru.rambler.alexeimohov.dto.MessageDto;
  @Getter
  @Setter
public class MessageSentEvent {

    private MessageDto messageDto;

    public MessageSentEvent(MessageDto messageDto) {
        this.messageDto = messageDto;
    }
}
