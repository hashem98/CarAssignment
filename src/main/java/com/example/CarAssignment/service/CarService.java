package com.example.CarAssignment.service;


import com.example.CarAssignment.model.Car;
import com.example.CarAssignment.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarService
{    @Autowired
    private CarRepository carRepository;


    public void saveCar(Car car)
    {
        carRepository.save(car);
    }

    public  void deleteCar(Long carID)
    {
        carRepository.deleteById(carID);
    }

    public Car getCar(long carID)
    {
       return carRepository.getOne(carID);
    }
    public List<Car> getCars()
    {
        return carRepository.findAll();
    }

}
