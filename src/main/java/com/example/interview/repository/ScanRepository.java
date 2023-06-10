package com.example.interview.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.interview.entity.Scan;

@Repository
public interface ScanRepository extends CrudRepository<Scan, Integer> {
}