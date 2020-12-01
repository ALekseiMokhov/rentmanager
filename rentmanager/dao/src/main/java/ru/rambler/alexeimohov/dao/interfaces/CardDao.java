package ru.rambler.alexeimohov.dao.interfaces;

import ru.rambler.alexeimohov.entities.Card;

import java.util.List;

public interface CardDao extends IGenericDao <Card> {

    Card findByCardNumber(long number);

    List<Card> findAllByUserName(String userName);
}
