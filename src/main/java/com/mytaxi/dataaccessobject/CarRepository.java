package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends CrudRepository<CarDO, Long>
{

    @Query("Select c from CarDO c Where c.engineType like :engineType and c.manufacturer like :manufacturer and c.seatCount >= :seatCount and c.rating >= :rating")
    List<CarDO> findAllByEngineTypeOrManufacturerOrGreaterThanSeatCountOrGreaterThanRating(
        @Param("engineType") String engineType,
        @Param("manufacturer") String manufacturer,
        @Param("seatCount") Integer seatCount,
        @Param("rating") Float rating);
}
