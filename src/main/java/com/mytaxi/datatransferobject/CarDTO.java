package com.mytaxi.datatransferobject;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO
{
    private Long id;
    private ZonedDateTime date_created;
    private String license_plate;
    private Integer seat_count;
    private boolean convertible;
    private String rating;
    private String engine_type;
    private String manufacturer;
}
