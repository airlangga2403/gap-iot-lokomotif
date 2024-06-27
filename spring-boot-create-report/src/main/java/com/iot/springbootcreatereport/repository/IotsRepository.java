package com.iot.springbootcreatereport.repository;

import com.iot.springbootcreatereport.dto.LocomotifIots;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IotsRepository extends MongoRepository<LocomotifIots, String> {
    long countByStatus(String status);

}
