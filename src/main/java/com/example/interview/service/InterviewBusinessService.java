package com.example.interview.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.interview.entity.Scan;
import com.example.interview.repository.ScanRepository;

@Service
public class InterviewBusinessService {

	@Autowired
    private ScanRepository scanRepository;
	
	private Logger LOGGER = Logger.getLogger(getClass());
	
	public List<Scan> getScansByFilter(String type, String startDate, String endDate) {
		List<Scan> scans = null;
		try {
	    	if(startDate != null && endDate != null) {
	    		Date dateStart=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
		    	Date dateEnd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
		    	if(dateStart != dateEnd && dateStart.getTime() < dateEnd.getTime()) {
		    		scans = (List<Scan>) scanRepository.getScanByFilters(type, dateStart, dateEnd);
		    	}
	    	}else {
	    		scans = (List<Scan>) scanRepository.getScanByType(type);
	    	}
    	}catch(IllegalArgumentException e) {
    		LOGGER.error("The date has not valid format");
    	}catch(Exception e) {
    		LOGGER.error(e.getMessage());
    	}
		return scans;
	}
}
