package ru.rambler.alexeimohov.dao.jpa.queries;

public interface CardQueries {
    String FIND_CARD_BY_NUMBER = "select c from Card c where c.creditCardNumber =:number";
    String FIND_ALL_CARDS = "select c from Card c";
}
