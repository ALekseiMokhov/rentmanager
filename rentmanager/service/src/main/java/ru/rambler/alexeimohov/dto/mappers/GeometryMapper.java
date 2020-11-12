package ru.rambler.alexeimohov.dto.mappers;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.springframework.stereotype.Component;
   @Component
public class GeometryMapper {

    public Point asPoint(String dto) throws ParseException {
        return (Point) new WKTReader(  ).read( dto );
    }

    public String asDto(Point point){
       return point.toText();
    }
}
