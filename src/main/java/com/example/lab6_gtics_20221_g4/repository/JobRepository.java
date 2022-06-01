package com.example.lab6_gtics_20221_g4.repository;

import com.example.lab6_gtics_20221_g4.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,String> {
}
