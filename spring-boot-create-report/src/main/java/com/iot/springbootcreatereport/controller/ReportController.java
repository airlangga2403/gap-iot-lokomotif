package com.iot.springbootcreatereport.controller;

import com.iot.springbootcreatereport.dto.LocomotifSummary;
import com.iot.springbootcreatereport.service.LocomotifService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@CrossOrigin
@Log4j2
public class ReportController {

    @Autowired
    private LocomotifService locomotifService;

    @GetMapping
    public ResponseEntity<LocomotifSummary> getReport() {
        log.info("success get API");
        return ResponseEntity.ok(locomotifService.getLatestLocomotif());
    }
}
