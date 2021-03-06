package ru.rambler.alexeimohov.entities.enums;

/*
 * Order @param totalPrice @linked to this @enum value.
 *Represents business value of User*/
public enum Privilege {
    NEWBIE(1.5), EXPERIENCED(1.0), EXCLUSIVE(0.8), PARTNER(0.5);
    private double coefficient;

    Privilege(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
