package com.example.interview.controller;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.interview.entity.Scan;
import com.example.interview.repository.ScanRepository;
import com.example.interview.service.InterviewBusinessService;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/scan") // This means URL's start with /demo (after Application path)
public class ScanController {

	@Autowired
    private ScanRepository scanRepository;
	
	@Autowired
	private InterviewBusinessService interviewBusinessService;
	
	private Logger LOGGER = Logger.getLogger(getClass());

    @GetMapping
    public ResponseEntity<List<Scan>> getAllScans() {
        List<Scan> scans = (List<Scan>) scanRepository.findAll();
        return new ResponseEntity<>(scans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scan> getScanById(@PathVariable("id") Long id) {
        Optional<Scan> scan = scanRepository.findById(Math.toIntExact(id));
        if (scan.isPresent()) {
            return new ResponseEntity<>(scan.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/filter")
    public ResponseEntity<List<Scan>> getAllScansByFilters(@RequestParam(required = true) String type, 
    		@RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
    	LOGGER.info("Getting all scans by filter");
    	List<Scan> scanList = interviewBusinessService.getScansByFilter(type, startDate, endDate);
    	if(scanList == null) {
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}else {
    		return new ResponseEntity<>(scanList, HttpStatus.OK);
    	}
    }
}
