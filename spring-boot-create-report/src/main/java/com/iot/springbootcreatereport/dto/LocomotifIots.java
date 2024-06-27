package com.iot.springbootcreatereport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "data-iots")
public class LocomotifIots {
    @Id
    private String id;

    @Field("kodeLoko")
    private String kodeLoko;

    @Field("namaLoko")
    private String namaLoko;

    @Field("dimensiLoko")
    private String dimensiLoko;

    @Field("status")
    private String status;

    @Field("dateAndTime")
    private Date dateAndTime;
}
