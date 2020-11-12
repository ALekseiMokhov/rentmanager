package ru.rambler.alexeimohov.dao.interfaces;

import ru.rambler.alexeimohov.entities.Card;

public interface CardDao extends IGenericDao <Card> {

    Card findByCardNumber(Long number);
}
