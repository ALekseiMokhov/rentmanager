package ru.rambler.alexeimohov.service.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.service.events.OrderCreatedEvent;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public interface IVehicleService {
    void remove(Long id);

    void saveOrUpdate(VehicleDto dto);

    void setDateForBooking(Long id, LocalDate d  );

    void cancelBooking(Long id, LocalDate date);

    @TransactionalEventListener
    void setAfterOrderCreated(OrderCreatedEvent event);

    VehicleDto getById(Long id);

    List <VehicleDto> getAll();

    List <VehicleDto> getAllChildish();

    List <VehicleDto> getAllMuscular();

    List <VehicleDto> getAllFromPoint(Long id);

    List <VehicleDto> getAllFreeFromPoint(Long id, LocalDate localDate);

    Set <String> getBookedDatesOfVehicle(long id);
}
