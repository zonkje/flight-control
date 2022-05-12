package aero.smart4aviation.flightcontrolapi.controler;

import aero.smart4aviation.flightcontrolapi.response.FlightWeightResponse;
import aero.smart4aviation.flightcontrolapi.service.FlightService;
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

    @GetMapping("/weight/{flightId}/{flightDate}")
    public ResponseEntity<FlightWeightResponse> getFlightWeight(
            @PathVariable("flightId") Long flightId,
            @PathVariable("flightDate") String flightDate
    ) {
        FlightWeightResponse response = flightService.getFlightWeight(flightId, flightDate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}