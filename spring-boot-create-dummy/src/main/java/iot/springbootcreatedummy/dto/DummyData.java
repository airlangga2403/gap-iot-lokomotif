package iot.springbootcreatedummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DummyData {
    private String kodeLoko;
    private String namaLoko;
    private String dimensiLoko;
    private String status;
    private Date dateAndTime;
}
