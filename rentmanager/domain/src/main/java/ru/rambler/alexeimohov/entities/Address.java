package ru.rambler.alexeimohov.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*
 * Class holding business address data of RentPoint.
 * Entity unidirectionaly @linked 1:1 to RentPoint, obtaining its @field Id.
 * Should be persisted together with RentPointDto*/
@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
@ToString(exclude = { "id", "rentPoint" })
@EqualsAndHashCode(exclude = { "id", "rentPoint" })
public class Address {
    @Id
    private Long id;

    @NotNull
    @Length(min = 3, max = 32)
    private String city;

    @NotNull
    @Length(min = 3, max = 32)
    private String street;

    @NotNull
    @Column(name = "building_number")
    private int buildingNumber;

    @MapsId
    @OneToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH })
    @JoinColumn(name = "id")
    private RentPoint rentPoint;


}
