package com.agm.h2.springboot.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "maximum_speed")
	private int maximum_speed;
	
	@Column(name = "engine")
	private float engine;
	
	@Column(name = "features")
	private String features;
	
	@Column(name = "launch_date")
	private String launch_date;
	
	@Column(name = "creation_date")
	private String creation_date;
	
	@Column(name = "last_consultation_date")
	private String last_consultation_date;
	
	public Car() {}

	public Car(long id, String brand, String model, int maximum_speed, float engine, String features,
			String launch_date, String creation_date, String last_consultation_date) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.maximum_speed = maximum_speed;
		this.engine = engine;
		this.features = features;
		this.launch_date = launch_date;
		this.creation_date = creation_date;
		this.last_consultation_date = last_consultation_date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMaximum_speed() {
		return maximum_speed;
	}

	public void setMaximum_speed(int maximum_speed) {
		this.maximum_speed = maximum_speed;
	}

	public float getEngine() {
		return engine;
	}

	public void setEngine(float engine) {
		this.engine = engine;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getLaunch_date() {
		return launch_date;
	}

	public void setLaunch_date(String launch_date) {
		this.launch_date = launch_date;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public String getLast_consultation_date() {
		return last_consultation_date;
	}

	public void setLast_consultation_date(String last_consultation_date) {
		this.last_consultation_date = last_consultation_date;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", maximum_speed=" + maximum_speed
				+ ", engine=" + engine + ", features=" + features + ", launch_date=" + launch_date + ", creation_date="
				+ creation_date + ", last_consultation_date=" + last_consultation_date + "]";
	}
	
}
