package ru.rambler.alexeimohov.dao.jpa;

import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.VehicleDao;
import ru.rambler.alexeimohov.dao.jpa.queries.VehicleQueries;
import ru.rambler.alexeimohov.entities.Vehicle;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository(value = "vehicleDao")
public class VehicleDaoJpaImpl extends GenericDaoJpa implements VehicleDao {


    @Override
    public void setDateBooked(long id, LocalDate date) {
        Vehicle vehicle = entityManager.find( Vehicle.class, id );
        vehicle.getBookedDates().add( date );
        entityManager.merge( vehicle );
    }

    @Override
    public boolean isBooked(long id, LocalDate date) {
        return entityManager.find( Vehicle.class, id )
                .getBookedDates()
                .contains( date );
    }

    @Override
    public Set <LocalDate> getBookedDates(long id) {
        return entityManager.find( Vehicle.class, id )
                .getBookedDates();
    }

    @Override
    public List <Vehicle> findAllChildish() {
        return entityManager.createQuery( VehicleQueries.SELECT_ALL_CHILDISH_VEHICLES )
                .getResultList();
    }

    @Override
    public List <Vehicle> findAllMuscular() {
        return entityManager.createQuery( VehicleQueries.SELECT_ALL_HUMAN_POWERED_VEHICLES )
                .getResultList();
    }

    @Override
    public List <Vehicle> findAllFromPoint(long id) {
        return entityManager.createQuery( VehicleQueries.SELECT_ALL_VEHICLES )
                .setParameter( "id", id )
                .getResultList();
    }

    @Override
    public List <Vehicle> findAllFreeFromPoint(long id, LocalDate date) {
        return entityManager.createQuery( VehicleQueries.SELECT_ALL_FREE_VEHICLES_BY_POINT )
                .setParameter( "id", id )
                .setParameter( "date", date )
                .getResultList();
    }

    @Override
    public Vehicle findById(long id) {
        return entityManager.find( Vehicle.class, id );

    }

    @Override
    public void remove(long id) {
        entityManager.remove( this.findById( id ) );
    }

    @Override
    public void save(Vehicle object) {
        entityManager.persist( object );
    }

    @Override
    public List <Vehicle> findAll() {
        return entityManager.createQuery( "Select v from Vehicle v" )
                .getResultList();
    }

    @Override
    public void update(Vehicle object) {
        entityManager.merge( object );
    }
}
