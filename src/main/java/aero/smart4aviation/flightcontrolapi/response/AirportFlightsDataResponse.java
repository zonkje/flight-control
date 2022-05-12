package aero.smart4aviation.flightcontrolapi.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportFlightsDataResponse {

    @ApiModelProperty("Number of flights departing from this airport")
    private Integer departuresNumber;
    @ApiModelProperty("Number of flights arriving to this airport")
    private Integer arrivalsNumber;
    @ApiModelProperty("Total number (pieces) of baggage arriving to this airport")
    private Integer arrivingBaggagePieces;
    @ApiModelProperty("Total number (pieces) of baggage departing from this airport")
    private Integer departingBaggagePieces;

}