package aero.smart4aviation.flightcontrolapi.controler;

import aero.smart4aviation.flightcontrolapi.response.AirportFlightsDataResponse;
import aero.smart4aviation.flightcontrolapi.response.FlightWeightDataResponse;
import aero.smart4aviation.flightcontrolapi.service.FlightService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @ApiOperation(value = "Get freight weight data for a given flight number and date")
    @GetMapping("/weight/{flightNumber}/{flightDate}")
    public ResponseEntity<FlightWeightDataResponse> getFlightWeight(
            @ApiParam(
                    value = "Unique number of flight",
                    example = "5110")
            @PathVariable("flightNumber") Long flightNumber,
            @ApiParam(
                    value = "Flight date in ISO 8601 format YYYY-MM-DDTHH:mm:ss",
                    example = "2015-09-07T10:10:42")
            @PathVariable("flightDate") String flightDate
    ) {
        FlightWeightDataResponse response = flightService.getFlightWeight(flightNumber, flightDate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Get arrivals and departures data for a given airport IATA code and date")
    @GetMapping("/airport/{airportIATACode}/{flightDate}")
    public ResponseEntity<AirportFlightsDataResponse> getAirportFlightsData(
            @ApiParam(
                    value = "IATA code of airport",
                    example = "SEA")
            @PathVariable("airportIATACode") String airportIATACode,
            @ApiParam(
                    value = "Flight date in ISO 8601 format YYYY-MM-DDTHH:mm:ss",
                    example = "2015-09-07T10:10:42")
            @PathVariable("flightDate") String flightDate
    ) {
        AirportFlightsDataResponse response = flightService.getAirportFlightsData(airportIATACode, flightDate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}