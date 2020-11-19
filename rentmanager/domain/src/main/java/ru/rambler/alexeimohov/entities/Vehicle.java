package ru.rambler.alexeimohov.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.rambler.alexeimohov.entities.enums.VehicleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Model name must be specified!")
    @Length(min = 3, max = 64)
    private String modelName;

    @NotNull
    @Column(name = "is_human_powered")
    private boolean isHumanPowered;

    @NotNull
    @Column(name = "is_childish")
    private boolean isChildish;

    @NotNull
    @Column(name = "is_free")
    private boolean isFree;

    @NotNull(message = "Vehicle rent price should be specified!")
    @Column(name = "rent_price")
    private double rentPrice;

    @NotNull
    @Column(name = "fine_price")
    private double finePrice;

    @NotNull(message = "Vehicle type should be specified!")
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_point_id")
    private RentPoint rentPoint;

}

