package com.mytaxi.domainobject;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_licence_plate", columnNames = {"licence_plate"})
)
public class CarDO
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(name = "licence_plate", nullable = false)
    @NotNull(message = "Licence Plate can not be null!")
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "Seat count can not be null!")
    private Integer seatCount;

    @Column(nullable = false)
    @NotNull(message = "Convertible can not be null!")
    private boolean convertible;

    @Column(nullable = false)
    @NotNull(message = "Rating can not be null!")
    private Float rating;

    @Column(nullable = false)
    @NotNull(message = "Engine Type can not be null!")
    private String engineType;

    @Column(nullable = false)
    @NotNull(message = "Manufacturer can not be null!")
    private String manufacturer;

}
