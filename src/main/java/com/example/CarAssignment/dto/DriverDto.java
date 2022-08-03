package com.example.CarAssignment.dto;



import com.example.CarAssignment.model.Car;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class DriverDto implements Serializable
{

    private String driverName;
    private Car car;
    private ZonedDateTime joiningDate;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ZonedDateTime getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(ZonedDateTime joiningDate) {
        this.joiningDate = joiningDate;
    }
}
