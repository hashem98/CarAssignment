package com.example.CarAssignment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Driver
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverID;
    private String driverName;
    private String  password;
    private String role;
    @JsonIgnore
    @OneToOne(mappedBy = "driver")
    private Car car;
    @CreationTimestamp
    private ZonedDateTime joiningDate;


    public Driver() {

    }

    public Driver(String driverName, Car car, ZonedDateTime joiningDate) {
        this.driverName = driverName;
        this.car = car;
        this.joiningDate = joiningDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDriverID() {
        return driverID;
    }

    public void setDriverID(Long driverID) {
        this.driverID = driverID;
    }

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
