package aero.smart4aviation.flightcontrolapi.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightWeightDataResponse {

    private Double cargoWeight;
    private Double baggageWeight;
    private Double totalWeight;
    private String weightUnit;

}
