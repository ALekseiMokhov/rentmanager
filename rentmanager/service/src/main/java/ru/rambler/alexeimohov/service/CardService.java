package ru.rambler.alexeimohov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.service.interfaces.ICardService;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class CardService implements ICardService {
}
