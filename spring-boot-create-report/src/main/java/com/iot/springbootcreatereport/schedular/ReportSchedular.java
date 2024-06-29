package com.iot.springbootcreatereport.schedular;

import com.iot.springbootcreatereport.config.TelegramBot;
import com.iot.springbootcreatereport.dto.LocomotifSummary;
import com.iot.springbootcreatereport.repository.LocomotifSummaryRepository;
import com.iot.springbootcreatereport.service.LocomotifService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
public class ReportSchedular {

    @Autowired
    private LocomotifService locomotifService;

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private LocomotifSummaryRepository locomotifSummaryRepository;

    @Scheduled(fixedRate = 60000) // tiap 10 detik generate report
    public void generateReport() {
        log.info("Running scheduler");
        boolean locoRunning = locomotifService.updateSummaryData();
        if (locoRunning) {
            Optional<LocomotifSummary> summaryOptional = locomotifSummaryRepository.findFirstByOrderByLastModifiedTimeDesc();
            if (summaryOptional.isPresent()) {
                LocomotifSummary summary = summaryOptional.get();
                log.info("Sending To Telegram Bot");
                telegramBot.sendTextMessage("481452322",
                        String.format("""
                                <====> Locomtif Summary <====>

                                Total Locomotif = %d
                                Total Poor Locomotif = %d
                                Total Good Locomotif = %d
                                Total Excellent Locomotif = %d
                                """, summary.getTotalLocomotif(), summary.getTotalLocomotifPoor(),
                                summary.getTotalLocomotifGood(), summary.getTotalLocomotifExcellent()));
                log.info("Scheduler completed");
            } else {
                log.error("No summary data found");
            }
        } else {
            log.error("Locomotif update failed");
        }
    }
}
