package ru.rambler.alexeimohov.dao.jpa;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.springframework.stereotype.Repository;
import ru.rambler.alexeimohov.dao.interfaces.RentPointDao;
import ru.rambler.alexeimohov.dao.jpa.queries.RentPointQueries;
import ru.rambler.alexeimohov.entities.RentPoint;

import java.util.List;
/*
 * RentPoint DAO JPA implementation. Uses JPQL queries to sort data
 * Uses com.vividsolutions.jts.io.WKTReader to convert com.vividsolutions.jts.geom.Point as @param for RentPoint*/
@Repository("rentPointDao")
public class RentPointDaoJpaImpl extends GenericDaoJpa implements RentPointDao {


    @Override
    public RentPoint getByCoordinate(Point point) {
        return (RentPoint) entityManager.createQuery( RentPointQueries.FIND_POINT_BY_COORDINATE )
                .setParameter( "coordinate", point );
    }

    @Override
    public RentPoint findById(long id) {
        return entityManager.find( RentPoint.class, id );
    }

    @Override
    public void remove(long id) {
        entityManager.remove( this.findById( id ) );
    }

    @Override
    public void save(RentPoint object) {
        object.setCoordinate( (Point) wktToGeometry( object.getCoordinate().toString() ) );
        entityManager.persist( object );
    }

    @Override
    public List <RentPoint> findAll() {
        return entityManager.createQuery( RentPointQueries.FIND_ALL_POINTS )
                .getResultList();
    }

    @Override
    public void update(RentPoint object) {
        entityManager.merge( object );
    }

    private Geometry wktToGeometry(String wktPoint) {
        WKTReader fromString = new WKTReader();
        Geometry geom = null;
        try {
            geom = fromString.read( wktPoint );
        } catch (ParseException e) {
            throw new RuntimeException( "Not a WKT string:" + wktPoint );
        }
        return geom;
    }

}
