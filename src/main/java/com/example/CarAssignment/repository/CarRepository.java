package com.example.CarAssignment.repository;

import com.example.CarAssignment.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long>
{
}
