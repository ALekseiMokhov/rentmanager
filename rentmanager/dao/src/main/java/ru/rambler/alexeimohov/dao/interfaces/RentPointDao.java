package ru.rambler.alexeimohov.dao.interfaces;

import com.vividsolutions.jts.geom.Point;
import ru.rambler.alexeimohov.entities.RentPoint;


public interface RentPointDao extends IGenericDao <RentPoint> {

    RentPoint getByCoordinate(Point point);
}
