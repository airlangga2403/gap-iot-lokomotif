package com.iot.springbootcreatereport.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "locomotif_summary")
public class LocomotifSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "total_locomotif")
    private int totalLocomotif;

    @Column(name = "total_locomotif_poor")
    private int totalLocomotifPoor; // count by

    @Column(name = "total_locomotif_good")
    private int totalLocomotifGood; // count by

    @Column(name = "total_locomotif_excellent")
    private int totalLocomotifExcellent; // count by

    @LastModifiedDate
    @Column(name = "last_modified_time")
    private LocalDateTime lastModifiedTime;
}
