package aero.smart4aviation.flightcontrolapi.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightWeightResponse {

    private Integer cargoWeight;
    private Integer baggageWeight;
    private Integer totalWeight;
    private String weightUnit;

}
