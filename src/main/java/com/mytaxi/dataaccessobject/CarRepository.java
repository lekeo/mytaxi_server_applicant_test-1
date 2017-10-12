package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDo;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<CarDo,Long>
{
}
