package com.agm.h2.springboot.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agm.h2.springboot.demo.entities.Car;
import com.agm.h2.springboot.demo.repository.CarRepository;
import com.agm.h2.springboot.demo.service.ICarService;

@Service
public class CarService implements ICarService{

	@Autowired
	CarRepository repository;
	
	@Override
	public List<Car> getAllCars() {
		return repository.findAll();
	}

	@Override
	public void saveCar(Car car) {
		repository.save(car);
	}

	@Override
	public void updateCar(Car car) {
		repository.save(car);
	}

	@Override
	public void deleteCarById(Long carId) {
		repository.deleteById(carId);
	}

	@Override
	public Optional<Car> getCarById(Long carId) {
		return repository.findById(carId);
	}

	@Override
	public void updateAllCars(List<Car> cars) {
		repository.saveAll(cars);
	}

}
