package com.agm.h2.springboot.demo.service;

import java.util.List;
import java.util.Optional;

import com.agm.h2.springboot.demo.entities.Car;

public interface ICarService {


	public List<Car> getAllCars();
	public Optional<Car> getCarById(Long carId);
	public void saveCar(Car car);
	public void updateCar(Car car);
	public void deleteCarById(Long carId);
	public void updateAllCars(List<Car> cars);
}
