package com.iot.springbootcreatereport.service;

import com.iot.springbootcreatereport.dto.LocomotifSummary;

public interface LocomotifService {
    Boolean updateSummaryData();

    LocomotifSummary getLatestLocomotif();
}
