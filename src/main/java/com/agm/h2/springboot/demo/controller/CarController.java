package com.agm.h2.springboot.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.agm.h2.springboot.demo.entities.Car;
import com.agm.h2.springboot.demo.errorHandling.dto.ServiceException;
import com.agm.h2.springboot.demo.errorHandling.handler.ServiceResponseHandler;
import com.agm.h2.springboot.demo.errorHandling.utils.CarRequestValidationUtil;
import com.agm.h2.springboot.demo.serviceImpl.CarService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CarController {

	@Autowired
	private CarService service;
	
	@Autowired
	private ServiceResponseHandler serviceResponse;
	
	@Autowired
	private CarRequestValidationUtil validationUtil;
	
	private LocalDateTime timestamp;
	private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
	
	@ApiOperation(value = "saveCarAPI", notes = "CURD Operations on H2 db and Spring Boot - Save Car Event", response = Car.class)
	@ApiResponses({@ApiResponse(code = 200, message = "Successfully saved car", response = Car.class),
		@ApiResponse(code = 400, message = "Invalid request", response = Car.class),
		@ApiResponse(code = 500, message = "internal Server Error", response= Car.class)})
	@PostMapping(value="/saveCar", produces = "application/json")
	public ResponseEntity<Object> saveCar(@Valid @RequestBody Car car){
		
		validationUtil.verifyCarId(car.getId());
		validationUtil.verifyString(car.getBrand());
		validationUtil.verifyString(car.getModel());
		
		timestamp = LocalDateTime.now();
		
		car.setCreation_date(timestamp.format(format));
		
		service.saveCar(car);
		
		return serviceResponse.buildSuccessResponseEntity(car, HttpStatus.OK);
	}
	
	@ApiOperation(value = "getAllCarsAPI", notes = "CURD Operations on H2 db and Spring Boot - Get Cars Event", response = Car.class)
	@ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched all cars", response = Car.class),
		@ApiResponse(code = 400, message = "Invalid request", response = Car.class),
		@ApiResponse(code = 500, message = "internal Server Error", response= Car.class)})
	@GetMapping(value="/getCars", produces = "application/json")
	public ResponseEntity<Object> getCars()throws ServiceException{
		
		List<Car> cars = service.getAllCars();
		
		timestamp = LocalDateTime.now();
		cars.forEach((c) -> c.setLast_consultation_date(timestamp.format(format)));
		service.updateAllCars(cars);
		return serviceResponse.buildSuccessResponseEntity(cars, HttpStatus.OK);
	}
	
	@ApiOperation(value = "getCarByIdAPI", notes = "CURD Operations on H2 db and Spring Boot - Get Car by Id Event", response = Car.class)
	@ApiResponses({@ApiResponse(code = 200, message = "Successfully fetched car", response = Car.class),
		@ApiResponse(code = 400, message = "Invalid request", response = Car.class),
		@ApiResponse(code = 500, message = "internal Server Error", response= Car.class)})
	@GetMapping(value="/getCarById/{carId}", produces = "application/json")
	public ResponseEntity<Object> getCarById(@PathVariable Long carId) throws ServiceException{
		
		validationUtil.verifyCarId(carId);
		Optional<Car> car = service.getCarById(carId);
		if(car.isPresent()) {
			timestamp = LocalDateTime.now();
			car.get().setLast_consultation_date(timestamp.format(format));
			service.updateCar(car.get());
			return serviceResponse.buildSuccessResponseEntity(car.get(), HttpStatus.OK);
		}else
			return serviceResponse.buildSuccessResponseEntity(carId, HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "updateCarAPI", notes = "CURD Operations on H2 db and Spring Boot - Update Car Event", response = Car.class)
	@ApiResponses({@ApiResponse(code = 200, message = "Successfully update car", response = Car.class),
		@ApiResponse(code = 400, message = "Invalid request", response = Car.class),
		@ApiResponse(code = 500, message = "internal Server Error", response= Car.class)})
	@PutMapping(value="/updateCar", produces = "application/json")
	public ResponseEntity<Object> updateCar(@Valid @RequestBody Car car) throws ServiceException{
		
		validationUtil.verifyCarId(car.getId());
		validationUtil.verifyString(car.getBrand());
		validationUtil.verifyString(car.getModel());
		Optional<Car> _car = service.getCarById(car.getId());
		if(_car.isPresent()) {
			service.updateCar(car);
			return serviceResponse.buildSuccessResponseEntity(car, HttpStatus.OK);
		}else
			return serviceResponse.buildSuccessResponseEntity(car, HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "deleteCarAPI", notes = "CURD Operations on H2 db and Spring Boot - Delete Car Event", response = Car.class)
	@ApiResponses({@ApiResponse(code = 200, message = "Successfully delete car", response = Car.class),
		@ApiResponse(code = 400, message = "Invalid request", response = Car.class),
		@ApiResponse(code = 500, message = "internal Server Error", response= Car.class)})
	@DeleteMapping(value="/deleteCarById/{carId}", produces = "application/json")
	public ResponseEntity<Object> deleteCarById(@PathVariable Long carId){
		validationUtil.verifyCarId(carId);
		Optional<Car> car = service.getCarById(carId);
		if(car.isPresent()) {
			service.deleteCarById(carId);
			return serviceResponse.buildSuccessResponseEntity(car, HttpStatus.OK);
		}else
			return serviceResponse.buildSuccessResponseEntity(carId, HttpStatus.NOT_FOUND);
	}
}
