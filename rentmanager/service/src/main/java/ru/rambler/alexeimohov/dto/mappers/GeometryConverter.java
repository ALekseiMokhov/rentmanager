package ru.rambler.alexeimohov.dto.mappers;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class GeometryConverter {
    public Point asPoint(String str) throws ParseException {
        return (Point) new WKTReader().read( str );
    }

    public String asDto(Point point) {
        return point.toText();
    }
}
