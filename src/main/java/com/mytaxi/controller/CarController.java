package com.mytaxi.controller;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDo;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cars")
public class CarController
{

    @Autowired
    private CarService carService;


    @GetMapping("/{carId}")
    public CarDTO getCar(@Valid @PathVariable Long carId) throws EntityNotFoundException
    {
        return CarMapper.makeCarDTO(carService.find(carId));
    }


    @GetMapping
    public List<CarDTO> getCars() throws EntityNotFoundException
    {
        return CarMapper.makeCarDTOList(carService.findAll());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO creteCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDo carDo = CarMapper.makeCarDo(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDo));
    }


    @DeleteMapping("/{carId}")
    public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }
}
