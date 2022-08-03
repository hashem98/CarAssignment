package com.example.CarAssignment.service;


import com.example.CarAssignment.model.Driver;
import com.example.CarAssignment.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService
{     @Autowired
    private DriverRepository driverRepository;

public void save(Driver driver)
{
    driverRepository.save(driver);
}

public Driver getDriver(Long driverID)
{
    return driverRepository.getOne(driverID);
}
public Driver getByName(String name)
{
    return driverRepository.findByDriverName(name);
}
  public  void deleteDriver(Long carID)
  {
      driverRepository.deleteById(carID);
  }
}
