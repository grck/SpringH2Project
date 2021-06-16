package com.agm.h2.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agm.h2.springboot.demo.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}
