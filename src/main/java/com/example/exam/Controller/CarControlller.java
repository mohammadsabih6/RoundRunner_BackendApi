package com.example.exam.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/rental")
public class CarControlller {
    @Autowired
    private CarsRepo carsRepo;

    @PostMapping("/add")
    public Cars addcars(@RequestBody Cars cars){
        return carsRepo.save(cars);
    }
    @GetMapping("/get")
    public List<Cars> getcars(@RequestBody Cars cars){
        return carsRepo.findAll();
    }
    @GetMapping("/get/{id}")
    public Optional<Cars> getcar(@PathVariable Long id){
        return carsRepo.findById(id);
    }
    
}
