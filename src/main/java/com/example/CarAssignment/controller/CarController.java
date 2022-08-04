package com.example.CarAssignment.controller;


import com.example.CarAssignment.dto.CarDto;
import com.example.CarAssignment.model.Car;
import com.example.CarAssignment.service.CarService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
@RestController
public class CarController
{
    @Autowired
     private CarService carService;

    @GetMapping("/getCar/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable("id")long carID)
    {   CarDto carDto=new CarDto();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(carService.getCar(carID),carDto);
        return new ResponseEntity(carDto,HttpStatus.OK);
    }

      @GetMapping("/getCars")
    public ResponseEntity<List<CarDto>> getCars()
      {
          List<CarDto> carDtoList=new ArrayList<>();
          ModelMapper mapper=new ModelMapper();
          for(Car car:carService.getCars())
          {
              CarDto carDto=new CarDto();
              mapper.map(car,carDto);
              carDtoList.add(carDto);
          }
          return new ResponseEntity<List<CarDto>>(carDtoList,HttpStatus.OK);
    }


    @PostMapping("/createCar")
    public ResponseEntity<?> addCar(Car car)
    {
       try {
           carService.saveCar(car);
           return new ResponseEntity("Car created successfully", HttpStatus.OK);
       }catch (Exception e)
       {
           return new ResponseEntity("Exception during car creation", HttpStatus.EXPECTATION_FAILED);
       }
    }


     @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id")long carID)
    {
        try {
            carService.deleteCar(carID);
            return new ResponseEntity<>("Car deleted successfully", HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>("Car not available", HttpStatus.EXPECTATION_FAILED);

        }
    }

    @PutMapping("/updateCar/{id}")
    public ResponseEntity<?> updateCar(@PathVariable("id")long carID,Car car)
    {   Car car1=carService.getCar(carID);
         if(car.getEngineType()!=null)
             car1.setEngineType(car.getEngineType());
        if(car.getLicensePlate()!=null)
            car1.setLicensePlate(car.getLicensePlate());

        if(car.getManufacturer()!=null)
            car1.setManufacturer(car.getManufacturer());
        if(car.getRating()!=0)
            car1.setRating(car.getRating());

        if(car.getSeats()!=0)
            car1.setSeats(car.getSeats());

        if(car.getConvertible()!=false)
            car1.setConvertible(car.getConvertible());

        carService.saveCar(car1);
        return new ResponseEntity<>("Updated successfully",HttpStatus.OK);

    }


}
