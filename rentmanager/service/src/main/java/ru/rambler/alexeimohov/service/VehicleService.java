package ru.rambler.alexeimohov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rambler.alexeimohov.dao.interfaces.VehicleDao;
import ru.rambler.alexeimohov.dto.VehicleDto;
import ru.rambler.alexeimohov.dto.mappers.interfaces.VehicleMapper;
import ru.rambler.alexeimohov.entities.Vehicle;
import ru.rambler.alexeimohov.service.events.OrderCreatedEvent;
import ru.rambler.alexeimohov.service.interfaces.IVehicleService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class VehicleService implements IVehicleService {

    private VehicleDao vehicleDao;

    private VehicleMapper vehicleMapper;

    public VehicleService(VehicleDao vehicleDao, VehicleMapper vehicleMapper) {
        this.vehicleDao = vehicleDao;
        this.vehicleMapper = vehicleMapper;
    }

    @Transactional(readOnly = false)
    @Override
    public void remove(Long id) {
        vehicleDao.remove( id );
    }

    @Transactional(readOnly = false)
    @Override
    public void saveOrUpdate(VehicleDto dto) {
        Vehicle vehicle = vehicleMapper.fromDto( dto );
        if (vehicle.getId() == null) {
            vehicleDao.save( vehicle );
        } else {
            vehicleDao.update( vehicle );
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void setDateForBooking(Long id, LocalDate date) {
        Vehicle vehicle = vehicleDao.findById( id );
        vehicle.getBookedDates().add( date );
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void setAfterOrderCreated(OrderCreatedEvent event) {
        Vehicle vehicle = vehicleDao.findById( Long.parseLong( event.getOrderDto().getVehicleDto().getId() ) );
        vehicle.getBookedDates().add( LocalDate.from( LocalDateTime.parse( event.getOrderDto().getStartTime() ) ) );

    }

    @Override
    public VehicleDto getById(Long id) {
        return vehicleMapper.toDto( vehicleDao.findById( id ) );
    }

    @Override
    public List <VehicleDto> getAll() {
        return vehicleMapper.listToDto( vehicleDao.findAll() );
    }

    @Override
    public List <VehicleDto> getAllChildish() {
        return vehicleMapper.listToDto( vehicleDao.findAllChildish() );
    }

    @Override
    public List <VehicleDto> getAllMuscular() {
        return vehicleMapper.listToDto( vehicleDao.findAllMuscular() );
    }

    @Override
    public List <VehicleDto> getAllFromPoint(Long id) {
        return vehicleMapper.listToDto( vehicleDao.findAllFromPoint( id ) );
    }

    @Override
    public List <VehicleDto> getAllFreeFromPoint(Long id, LocalDate date) {
        return vehicleMapper.listToDto( vehicleDao.findAllFreeFromPoint( id, date ) );
    }
}
