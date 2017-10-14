package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper
{
    public static CarDTO makeCarDTO(CarDO carDo)
    {
        return CarDTO.builder().id(carDo.getId())
            .convertible(carDo.isConvertible())
            .dateCreated(carDo.getDateCreated().toString())
            .engineType(carDo.getEngineType().toString())
            .licensePlate(carDo.getLicensePlate())
            .rating(carDo.getRating())
            .seatCount(carDo.getSeatCount())
            .manufacturer(carDo.getManufacturer())
            .build();
    }


    public static CarDO makeCarDo(CarDTO carDTO)
    {
        return CarDO.builder()
            .id(carDTO.getId())
            .convertible(carDTO.isConvertible())
            .engineType(carDTO.getEngineType())
            .licensePlate(carDTO.getLicensePlate())
            .manufacturer(carDTO.getManufacturer())
            .seatCount(carDTO.getSeatCount())
            .rating(carDTO.getRating())
            .build();
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars)
    {
        return cars.stream()
            .map(CarMapper::makeCarDTO)
            .collect(Collectors.toList());
    }
}
