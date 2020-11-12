package ru.rambler.alexeimohov.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*
 * Entity unidirectionaly linked 1:1 to Rent_point obtaining its Id*/
@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
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
    @Length(min = 3, max = 32)
    @Column(name = "building_number")
    private int buildingNumber;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private RentPoint rentPoint;


}
