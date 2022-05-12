package aero.smart4aviation.flightcontrolapi.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportFlightsDataResponse {

    private Integer departuresNumber;
    private Integer arrivalsNumber;
    private Integer arrivingBaggagePieces;
    private Integer departingBaggagePieces;

}