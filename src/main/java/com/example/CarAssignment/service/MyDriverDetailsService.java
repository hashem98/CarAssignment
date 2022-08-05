package com.example.CarAssignment.service;

import com.example.CarAssignment.model.Driver;
import com.example.CarAssignment.model.DriverPrincipal;
import com.example.CarAssignment.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyDriverDetailsService implements UserDetailsService
{   @Autowired
    private DriverRepository driverRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Driver driver=driverRepository.findByDriverName(s);
        if(driver==null)

                throw  new UsernameNotFoundException("Driver 404");

        return new DriverPrincipal(driver.getDriverName(),driver.getPassword(),driver.getRole() );

    }
}
