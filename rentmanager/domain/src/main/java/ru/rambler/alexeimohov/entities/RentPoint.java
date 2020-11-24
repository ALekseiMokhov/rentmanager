package ru.rambler.alexeimohov.entities;

import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.rambler.alexeimohov.entities.enums.PointType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
    Entity representing scooter sharing place with com.vividsolutions.jts.geom.
    Point  mapped as GeometryType related 1:1 Address, 1:n Vehicle bidirectional.
    Nested comparator comparing by PointType field pointValue
 * */
@Entity
@Table(name = "rent_point")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "id", "address" })
public class RentPoint {
    public static Comparator <RentPoint> pointValueComparator = new Comparator <RentPoint>() {
        @Override
        public int compare(RentPoint p1, RentPoint p2) {
            return (int) (p1.getType().getPointValue() - p2.getType().getPointValue());
        }
    };
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Rent point name should'not be null")
    @Column(name = "point_name")
    private String pointName;
    @Enumerated(EnumType.STRING)
    private PointType type;
    @Column(name = "coordinate")
    @NotNull(message = "Coordinate of renting point must be specified!")
    private Point coordinate;
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "rentPoint",
            cascade = CascadeType.ALL)
    private List <Vehicle> vehicles;

    public RentPoint() {
        this.vehicles = new ArrayList <>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add( vehicle );
        vehicle.setRentPoint( this );
    }

    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove( vehicle );
        vehicle.setRentPoint( null );
    }
}
