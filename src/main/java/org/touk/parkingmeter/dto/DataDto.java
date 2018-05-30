package org.touk.parkingmeter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataDto {

    @JsonProperty("longitude")
    private Long longitude;
    @JsonProperty("latitude")
    private Long latitude;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("plate")
    private String plate;
}
