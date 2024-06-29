package com.iot.springbootcreatereport.service;

import static org.junit.jupiter.api.Assertions.*;


import com.iot.springbootcreatereport.dto.LocomotifSummary;
import com.iot.springbootcreatereport.repository.IotsRepository;
import com.iot.springbootcreatereport.repository.LocomotifSummaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.iot.springbootcreatereport.utils.Constant.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocomotifServiceImplTest {

    @Mock
    private IotsRepository locomotifRepository;

    @Mock
    private LocomotifSummaryRepository locomotifSummaryRepository;

    @InjectMocks
    private LocomotifServiceImpl locomotifService;

   @Test
    void testUpdateSummaryData() {
        when(locomotifRepository.count()).thenReturn(10L);
        when(locomotifRepository.countByStatus(CONSTANT_POOR)).thenReturn(3L);
        when(locomotifRepository.countByStatus(CONSTANT_GOOD)).thenReturn(5L);
        when(locomotifRepository.countByStatus(CONSTANT_EXCELENT)).thenReturn(2L);

        boolean result = locomotifService.updateSummaryData();

        assertTrue(result);
        verify(locomotifSummaryRepository, times(1)).save(any(LocomotifSummary.class));
    }

    @Test
    void testUpdateSummaryData_Exception() {
        when(locomotifRepository.count()).thenThrow(new RuntimeException("Database error"));

        boolean result = locomotifService.updateSummaryData();

        assertFalse(result);
        verify(locomotifSummaryRepository, times(0)).save(any(LocomotifSummary.class));
    }

    @Test
    void testGetLatestLocomotif() {
        LocomotifSummary summary = LocomotifSummary.builder()
                .totalLocomotif(10)
                .totalLocomotifPoor(3)
                .totalLocomotifGood(5)
                .totalLocomotifExcellent(2)
                .lastModifiedTime(LocalDateTime.now())
                .build();
        when(locomotifSummaryRepository.findFirstByOrderByLastModifiedTimeDesc()).thenReturn(Optional.of(summary));

        LocomotifSummary result = locomotifService.getLatestLocomotif();

        assertNotNull(result);
        assertEquals(10, result.getTotalLocomotif());
    }

    @Test
    void testGetLatestLocomotif_Empty() {
        when(locomotifSummaryRepository.findFirstByOrderByLastModifiedTimeDesc()).thenReturn(Optional.empty());

        LocomotifSummary result = locomotifService.getLatestLocomotif();

        assertNull(result);
    }

    @Test
    void testGetLatestLocomotif_Exception() {
        when(locomotifSummaryRepository.findFirstByOrderByLastModifiedTimeDesc()).thenThrow(new RuntimeException("Database error"));

        LocomotifSummary result = locomotifService.getLatestLocomotif();

        assertNull(result);
    }
}