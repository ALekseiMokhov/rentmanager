package ru.rambler.alexeimohov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.service.interfaces.IMessageService;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class MessageService implements IMessageService {
}
