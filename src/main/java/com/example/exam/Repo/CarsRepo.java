package com.example.exam.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exam.Model.Cars;

public interface CarsRepo extends JpaRepository<Cars,Long> {
    
}
