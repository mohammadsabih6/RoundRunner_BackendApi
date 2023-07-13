package com.example.exam.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exam.Model.RentalFrom;

public interface RentalFormRepo extends JpaRepository<RentalFrom,Long> {
    
}
