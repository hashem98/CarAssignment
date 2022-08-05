package com.example.CarAssignment.controller;


import com.example.CarAssignment.dto.DriverDto;
import com.example.CarAssignment.exception.CarAlreadyInUseException;
import com.example.CarAssignment.model.AuthenticationRequest;
import com.example.CarAssignment.model.AuthenticationResponse;
import com.example.CarAssignment.model.Car;
import com.example.CarAssignment.model.Driver;
import com.example.CarAssignment.service.CarService;
import com.example.CarAssignment.service.DriverService;
import com.example.CarAssignment.service.JwtUtil;
import com.example.CarAssignment.service.MyDriverDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DriverController
{   @Autowired
    private DriverService driverService;

      @Autowired
     private CarService carService;
       @Autowired
      private BCryptPasswordEncoder bCryptPasswordEncoder;
       @Autowired
       private AuthenticationManager authenticationManager;
       @Autowired
       private MyDriverDetailsService userDetailsService;
          @Autowired
       private JwtUtil jwtTokenUtil;
      @PostMapping("/authenticate")
       public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
       {
           try{
               authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
               );
           }catch (BadCredentialsException e)
           {
               throw new Exception("Incorrect username or password",e);
           }
           final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

      final  String jwt=jwtTokenUtil.generateToken(userDetails);
           return  ResponseEntity.ok(new AuthenticationResponse(jwt));
       }


    @PostMapping("/createDriver")
    public ResponseEntity createDriver(Driver driver)
    {
         String password=bCryptPasswordEncoder.encode(driver.getPassword());

          driver.setPassword(password);
          driver.setRole("driver");
        driverService.save(driver);
        return new ResponseEntity<>("Created successfully", HttpStatus.OK);
    }

      @GetMapping("/getDriver/{id}")
      public ResponseEntity<DriverDto> getDriver(@PathVariable("id")long driverID)
      {
          DriverDto driverDto=new DriverDto();
          ModelMapper modelMapper=new ModelMapper();
          modelMapper.map(driverService.getDriver(driverID),driverDto);
          System.out.println(driverDto.getCar().getCarID());
          return new ResponseEntity(driverDto,HttpStatus.OK);
      }

    @GetMapping("/getDrivers")
    public ResponseEntity<List<DriverDto>> getDrivers()
    {
        List<DriverDto> driverDtoList=new ArrayList<>();
        ModelMapper mapper=new ModelMapper();
        for(Car car:carService.getCars())
        {
            DriverDto driverDto=new DriverDto();
            mapper.map(car,driverDto);
            driverDtoList.add(driverDto);
        }
        return new ResponseEntity<List<DriverDto>>(driverDtoList,HttpStatus.OK);
    }

    @DeleteMapping("/deleteDriver/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable("id")long driverID)
    {
          driverService.deleteDriver(driverID);
          return new ResponseEntity("Deleted successfully",HttpStatus.OK);
    }

    @PatchMapping("/selectCar/{id}")
    public ResponseEntity<?> selectCar(@PathVariable("id")long carID,Principal principal)
    {
        String name=principal.getName();
        Car car=carService.getCar(carID);
        Driver driver=driverService.getByName(name);
        if(car.getDriver()==null)
        {
            System.out.println(driver.getDriverID());
            System.out.println(car.getCarID());

            car.setDriver(driver);
            carService.saveCar(car);
            return new ResponseEntity("Car selected successfully",HttpStatus.OK);
        }
        else {
            throw new CarAlreadyInUseException("Car is already in USE");
        }
    }

    @PatchMapping("/deSelectCar")
    public ResponseEntity<?> deSelectCar(Principal principal)
    {   String name =principal.getName();
        Driver driver=driverService.getByName(name);
        Car car;
        //System.out.println(carService.getCar(driver.getCar().getCarID()));
        try {
            car = carService.getCar(driver.getCar().getCarID());
        }catch (Exception e)
        {
            return new ResponseEntity("Car not exits",HttpStatus.METHOD_NOT_ALLOWED);

        }

        if(car.getDriver()!=null)
        {
            car.setDriver(null);
            carService.saveCar(car);
            return new ResponseEntity("Car deselected",HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity("Car not exits",HttpStatus.METHOD_NOT_ALLOWED);
        }

    }
}
