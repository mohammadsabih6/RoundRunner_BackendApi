package com.example.exam.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exam.Model.Cars;
import com.example.exam.Repo.CarsRepo;
@CrossOrigin("*")
@RestController
@RequestMapping("/cars")
public class CarControlller {
    @Autowired
    private CarsRepo carsRepo;

    @PostMapping("/add")
    public Cars addcars(@RequestBody Cars cars){
        return carsRepo.save(cars);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getData(){
        List<Cars> car=carsRepo.findAll();
        if(!car.isEmpty()){
            return ResponseEntity.ok(car);
        }
        else{
            String errorMessage = "Unable to get Counselor data";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
   
    @GetMapping("/get/{id}")
    public Optional<Cars> getcar(@PathVariable Long id){
        return carsRepo.findById(id);
    }
    
}
