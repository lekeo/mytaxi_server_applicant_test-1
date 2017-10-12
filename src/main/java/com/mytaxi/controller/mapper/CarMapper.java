package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDo;
import com.mytaxi.domainobject.EngineType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper
{
    public static CarDTO makeCarDTO(CarDo carDo)
    {
        return CarDTO.builder().id(carDo.getId())
            .convertible(carDo.isConvertible())
            .date_created(carDo.getDate_created())
            .engine_type(carDo.getEngine_type().toString())
            .license_plate(carDo.getLicense_plate())
            .rating(carDo.getRating())
            .build();
    }


    public static CarDo makeCarDo(CarDTO carDTO)
    {
        return CarDo.builder()
            .convertible(carDTO.isConvertible())
            .date_created(carDTO.getDate_created())
            .engine_type(EngineType.valueOf(carDTO.getEngine_type()))
            .license_plate(carDTO.getLicense_plate())
            .manufacturer(carDTO.getManufacturer())
            .build();
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDo> cars)
    {
        return cars.stream()
            .map(CarMapper::makeCarDTO)
            .collect(Collectors.toList());
    }
}
