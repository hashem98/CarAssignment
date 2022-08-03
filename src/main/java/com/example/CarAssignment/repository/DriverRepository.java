package com.example.CarAssignment.repository;

import com.example.CarAssignment.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Long>
{
    Driver findByDriverName(String s);
}
