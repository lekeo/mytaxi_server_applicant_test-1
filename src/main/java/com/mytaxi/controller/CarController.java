package com.mytaxi.controller;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cars")
public class CarController
{

    @Autowired
    private CarService carService;


    @GetMapping("/{carId}")
    @ApiOperation(value = "Get one car by carId", response = CarDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "resource not found")
    })
    public CarDTO getCar(@Valid @PathVariable Long carId) throws EntityNotFoundException
    {
        return CarMapper.makeCarDTO(carService.find(carId));
    }


    @GetMapping
    @ApiOperation(value = "Get cars with parameters", response = CarDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "resource not found")
    })
    public List<CarDTO> getCars(
        @ApiParam(value = "Car engine type") @Param(value = "engineType") String engineType,
        @ApiParam(value = "Car manufacturer") @Param(value = "manufacturer") String manufacturer,
        @ApiParam(value = "Car minimun seats") @Param(value = "seatCount") Integer seatCount,
        @ApiParam(value = "Car minimun ratings") @Param(value = "rating") Float rating) throws EntityNotFoundException
    {

        if (engineType == null || engineType.equals(""))
        {
            engineType = "%";
        }
        if (manufacturer == null || manufacturer.equals(""))
        {
            manufacturer = "%";
        }
        if (seatCount == null)
        {
            seatCount = 0;
        }
        if (rating == null)
        {
            rating = 0.0F;
        }
        return CarMapper.makeCarDTOList(carService.findAllByEngineTypeOrManufacturerOrGreaterThanSeatCountOrGreaterThanRating(
            engineType, manufacturer, seatCount, rating));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Persist a new Car", response = CarDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 404, message = "resource not found")
    })
    public CarDTO creteCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDo = CarMapper.makeCarDo(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDo));
    }


    @DeleteMapping("/{carId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete one Car", response = CarDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 404, message = "resource not found")
    })
    public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }
}
