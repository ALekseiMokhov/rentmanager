package ru.rambler.alexeimohov.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class PointConverter {

    public static Point build(int x, int y) {
        Coordinate coordinate = new Coordinate( x, y );
        Point point = new GeometryFactory().createPoint( coordinate );
        return point;
    }

    public static Point fromDto(String input) throws ParseException {
        return (Point) new WKTReader().read( input );
    }
}
