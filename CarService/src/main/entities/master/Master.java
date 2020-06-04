package main.entities.master;

import java.time.LocalDate;
import java.util.UUID;

public interface Master {
    UUID getId();

    String getFullName();

    double getDailyPayment();

    boolean isFreeForDate(LocalDate date);

    void bookMaster(LocalDate date);

    void unBookMaster(LocalDate date);

}
