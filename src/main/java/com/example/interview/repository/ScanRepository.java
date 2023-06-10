package com.example.interview.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.interview.entity.Scan;

@Repository
public interface ScanRepository extends CrudRepository<Scan, Integer> {
	
	@Query(nativeQuery=true, value="select * from scan where type =:type")
    public List<Scan> getScanByType(String type);
	
	@Query(nativeQuery=true, value="select * from scan where type =:type and timestamp between :startDate and :endDate")
    public List<Scan> getScanByFilters(String type, Date startDate, Date endDate);
}