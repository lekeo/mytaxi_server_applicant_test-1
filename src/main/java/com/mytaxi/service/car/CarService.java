package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDo;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;

public interface CarService
{

    CarDo find(Long carId) throws EntityNotFoundException;

    CarDo create(CarDo carDo) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    List<CarDo> findAll();
}
