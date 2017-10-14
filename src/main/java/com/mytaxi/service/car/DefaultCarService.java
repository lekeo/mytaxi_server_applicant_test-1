package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DefaultCarService implements CarService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

    @Autowired
    private CarRepository carRepository;


    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    @Override
    public CarDO create(CarDO carDo) throws ConstraintsViolationException
    {
        CarDO car;
        try
        {
            if (carDo.getDateCreated() == null ){
                carDo.setDateCreated(ZonedDateTime.now());
            }
            car = carRepository.save(carDo);

        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }


    @Override
    public void delete(Long carId) throws EntityNotFoundException
    {
        carRepository.delete(carId);
    }


    @Override
    public List<CarDO> findAllByEngineTypeOrManufacturerOrGreaterThanSeatCountOrGreaterThanRating(String engineType, String manufacturer, Integer seatcount, Float rating)
    {
        return carRepository.findAllByEngineTypeOrManufacturerOrGreaterThanSeatCountOrGreaterThanRating(engineType,manufacturer,seatcount,rating);
    }


    private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        CarDO carDo = carRepository.findOne(carId);
        if (carDo == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + carId);
        }
        return carDo;
    }

}
