package com.mytaxi.service.driver

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import com.mytaxi.dataaccessobject.DriverRepository
import com.mytaxi.domainobject.CarDO
import com.mytaxi.domainobject.DriverDO
import com.mytaxi.domainvalue.OnlineStatus
import com.mytaxi.exception.CarAlreadyInUseException
import com.mytaxi.exception.EntityNotFoundException
import com.mytaxi.service.car.CarService
import spock.lang.Specification

class DefaultDriverServiceSpec extends Specification{

    private DriverRepository driverRepository = Mock(DriverRepository)
    private CarService carService = Mock(CarService)
    private DriverService driverService = new
            DefaultDriverService(driverRepository: driverRepository,carService: carService)

    def setupSpec(){
        FixtureFactoryLoader.loadTemplates("com.mytaxi.domainobject.templates")
    }

    def "when I try to select a valid car"(){
        given:"I have a valid ONLINE driveId and carId"
        Long driverId = 10L
        Long carId = 50
        driverRepository.findOne(_ as Long) >> {
            DriverDO driverDO = Fixture.from(DriverDO.class).gimme("driver1")
            return  driverDO
        }
        carService.find(_ as Long) >> {
            CarDO carDo = Fixture.from(CarDO.class).gimme("car1")
            return carDo
        }
        when: "I call the method select car in DefaultDriveService"
        DriverDO driverDO = driverService.selectCar(driverId,carId)
        then: "Driver return with a car selected"
        driverDO.selectedCar != null
    }

    def "when I try to select a valid car with a OFFLINE driver"(){
        given:"I have a OFFLINE valid driveId  and carId"
        Long driverId = 30L
        Long carId = 50
        driverRepository.findOne(_ as Long) >> {
            DriverDO driverDO = Fixture.from(DriverDO.class).gimme("driver3")
            return  driverDO
        }
        carService.find(_ as Long) >> {
            CarDO carDo = Fixture.from(CarDO.class).gimme("car1")
            return carDo
        }
        when: "I call the method select car in DefaultDriveService"
        DriverDO driverDO = driverService.selectCar(driverId,carId)
        then: "Driver return with a car selected"
        driverDO.selectedCar == null
    }
    
    def "When I try to select a car and another driver already selected"(){
        given: "I have a valid ONLINE driveId and carId"
        Long driverId = 30L
        Long carId = 70L
        driverRepository.findOne (_ as Long) >> {
            DriverDO driverDO = Fixture.from(DriverDO.class).gimme("driver1")
            return  driverDO
        }

        driverRepository.findByOnlineStatus (_ as OnlineStatus) >> {
            return [Fixture.from(DriverDO.class).gimme("driver1"),
                    Fixture.from(DriverDO.class).gimme("driver2"),
                    Fixture.from(DriverDO.class).gimme("driver3")]
        }
        carService.find(_ as Long) >> {
            CarDO carDo = Fixture.from(CarDO.class).gimme("car3")
            return carDo
        }
        when: "I call the method select car in DefaultDriveService"
        DriverDO driverDO = driverService.selectCar(driverId,carId)
        then: "Driver return with a car selected"
        thrown(CarAlreadyInUseException)
    }

    def "When I try to deselect a car with a valid driverId"(){
        given:"I have a valid ONLINE driveId and carId"
        Long driverId = 20L
        driverRepository.findOne(_ as Long) >> {
            DriverDO driverDO = Fixture.from(DriverDO.class).gimme("driver2")
            return  driverDO
        }
        driverRepository.save(_ as DriverDO) >> {
            DriverDO driverDO = Fixture.from(DriverDO.class).gimme("driver2")
            driverDO.selectedCar = null
            return  driverDO
        }
        when: "I call the method select car in DefaultDriveService"
        DriverDO driverDO = driverService.deselectCar(driverId)
        then: "Driver return with a car selected"
        driverDO.selectedCar == null
    }

    def "When I try to deselect a car with a invalid driverId"(){
        given:"I have a valid ONLINE driveId and carId"
        Long driverId = 20L
        driverRepository.findOne(_ as Long) >> {
            return null
        }
        driverRepository.save(_ as DriverDO) >> {
            DriverDO driverDO = Fixture.from(DriverDO.class).gimme("driver2")
            driverDO.selectedCar = null
            return  driverDO
        }
        when: "I call the method select car in DefaultDriveService"
        DriverDO driverDO = driverService.deselectCar(driverId)
        then: "Driver return with a car selected"
        thrown(EntityNotFoundException)
    }
}
