package aero.smart4aviation.flightcontrolapi.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightWeightDataResponse {

    @ApiModelProperty("Cargo Weight for requested flight")
    private Double cargoWeight;
    @ApiModelProperty("Baggage Weight for requested flight")
    private Double baggageWeight;
    @ApiModelProperty("Total Weight for requested flight")
    private Double totalWeight;
    private String weightUnit;

}
