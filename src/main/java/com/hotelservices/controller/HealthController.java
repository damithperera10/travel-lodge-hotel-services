package com.hotelservices.controller;

import com.hotelservices.dto.HealthDTO;
import com.hotelservices.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/health" })
/**
 *
 * the class HealthController
 *
 */
public class HealthController {

    @Autowired
    private HealthService healthService;

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<HealthDTO> getEventProcessorHealth() {
        HealthDTO healthDto = healthService.getHealth();
        return new ResponseEntity<>(healthDto, HttpStatus.OK);
    }
}

