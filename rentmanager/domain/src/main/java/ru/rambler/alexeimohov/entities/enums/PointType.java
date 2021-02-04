package ru.rambler.alexeimohov.entities.enums;

import lombok.Getter;

/*
 * Order @param totalPrice @linked to this @enum value.*/
public enum PointType {
    CENTER(5),
    AIRPORT(3.5),
    SECOND_LINE(2.5),
    SUBURBIA(2);

    @Getter
    private final double pointValue;

    PointType(double pointValue) {
        this.pointValue = pointValue;
    }
}
