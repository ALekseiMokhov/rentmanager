package ru.rambler.alexeimohov.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.rambler.alexeimohov.entities.enums.VehicleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Model name must be specified!")
    @Length(min = 3, max = 64)
    @Column(name = "model_name")
    private String modelName;

    @NotNull
    @Column(name = "is_human_powered")
    private boolean isHumanPowered;

    @NotNull
    @Column(name = "is_childish")
    private boolean isChildish;

    @NotNull(message = "Vehicle rent price should be specified!")
    @Column(name = "rent_price")
    private double rentPrice;

    @NotNull
    @Column(name = "fine_price")
    private double finePrice;

    @NotNull
    @ElementCollection (fetch = FetchType.LAZY)
    @CollectionTable(
            name = "vehicle_booked_dates",
            joinColumns=@JoinColumn(name = "id", referencedColumnName = "id")
    )
    @Column(name="booked_dates")
    private Set <LocalDate> bookedDates ;

    @NotNull(message = "Vehicle type should be specified!")
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_point_id")
    private RentPoint rentPoint;

    public Vehicle() {
        this.bookedDates = new HashSet <>();
    }
}

