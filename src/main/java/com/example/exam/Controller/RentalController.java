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
import com.example.exam.Model.RentalFrom;
import com.example.exam.Repo.CarsRepo;
import com.example.exam.Repo.RentalFormRepo;
@RestController
@CrossOrigin("*")
@RequestMapping("/rental")
public class RentalController {
    @Autowired
    private RentalFormRepo RentalfromRepo;

    @PostMapping("/add")
    public RentalFrom addcars(@RequestBody RentalFrom rentalFrom){
        return RentalfromRepo.save(rentalFrom);
    }
    @GetMapping("/get")
    public List<RentalFrom> getcars(@RequestBody RentalFrom rentalFrom){
        return RentalfromRepo.findAll();
    }

}
