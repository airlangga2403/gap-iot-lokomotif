package com.iot.springbootcreatereport.service;

import com.iot.springbootcreatereport.dto.LocomotifSummary;
import com.iot.springbootcreatereport.repository.IotsRepository;
import com.iot.springbootcreatereport.repository.LocomotifSummaryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.iot.springbootcreatereport.utils.Constant.*;

@Service
@Log4j2
public class LocomotifServiceImpl implements LocomotifService {


    @Autowired
    private IotsRepository locomotifRepository;

    @Autowired
    private LocomotifSummaryRepository locomotifSummaryRepository;

    @Override
    public Boolean updateSummaryData() {
        try {
            log.info("generating summary report");
            LocomotifSummary locoData = LocomotifSummary.builder()
                    .totalLocomotif((int) locomotifRepository.count())
                    .totalLocomotifPoor((int) locomotifRepository.countByStatus(CONSTANT_POOR))
                    .totalLocomotifGood((int) locomotifRepository.countByStatus(CONSTANT_GOOD))
                    .totalLocomotifExcellent((int) locomotifRepository.countByStatus(CONSTANT_EXCELENT))
                    .lastModifiedTime(LocalDateTime.now())
                    .build();

            log.info("saving to postgress");
            locomotifSummaryRepository.save(locoData);
            log.info("Summary report generated successfully");

            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public LocomotifSummary getLatestLocomotif() {
        LocomotifSummary locomotifSummary = null; // Initialize with null

        try {
            Optional<LocomotifSummary> locomotifSummaryOptional = locomotifSummaryRepository.findFirstByOrderByLastModifiedTimeDesc();
            if (locomotifSummaryOptional.isPresent()) {
                locomotifSummary = locomotifSummaryOptional.get(); // Assign the value if present
            } else {
                log.info("empty data");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return locomotifSummary;
    }
}
