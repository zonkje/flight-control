package aero.smart4aviation.flightcontrolapi.service;

import aero.smart4aviation.flightcontrolapi.exception.ResourceNotFoundException;
import aero.smart4aviation.flightcontrolapi.exception.UnrecognizedWeightUnitException;
import aero.smart4aviation.flightcontrolapi.model.Cargo;
import aero.smart4aviation.flightcontrolapi.model.Flight;
import aero.smart4aviation.flightcontrolapi.repository.BaggageRepository;
import aero.smart4aviation.flightcontrolapi.repository.CargoRepository;
import aero.smart4aviation.flightcontrolapi.repository.FlightRepository;
import aero.smart4aviation.flightcontrolapi.response.FlightWeightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final BaggageRepository baggageRepository;
    private final CargoRepository cargoRepository;

    public FlightWeightResponse getFlightWeight(Long flightId, String flightDate) {
            Flight flight = flightRepository.findById(flightId)
                    .orElseThrow(() -> new ResourceNotFoundException("Flight", flightId));

            final Double poundToKgRate = 0.45359237;
            Double flightCargoWeightKgs = 0.0d;
            Double flightBaggageWeightKgs = 0.0d;
        countFreightWeight(flight, poundToKgRate, flightCargoWeightKgs);

        return FlightWeightResponse.builder()
                    .weightUnit("kg")
                    .build();
    }

    private void countFreightWeight(Flight flight, Double poundToKgRate, Double flightCargoWeightKgs) {
        for(Cargo cargo : flight.getCargo()){
            String unit = cargo.getWeightUnit();
            if(unit.equals("kg")){
                flightCargoWeightKgs += cargo.getWeight();
            } else if(unit.equals("lb")){
                flightCargoWeightKgs += cargo.getWeight()* poundToKgRate;
            } else {
                throw new UnrecognizedWeightUnitException(unit);
            }
        }
    }

    public void saveJSONData(Collection<Flight> flights) {
        flights
                .forEach(flight -> {
                            if (flightRepository.existsById(flight.getFlightId())) {
                                Flight flightData = flightRepository.findById(flight.getFlightId())
                                        .orElseThrow(() -> new ResourceNotFoundException("Flight", flight.getFlightId()));
                                if (flight.getBaggage() != null) baggageRepository.saveAll(flight.getBaggage());
                                if (flight.getCargo() != null) cargoRepository.saveAll(flight.getCargo());
                                flight.setFlightId(flightData.getFlightId());
                                flight.setFlightNumber(flightData.getFlightNumber());
                                flight.setArrivalAirportIATACode(flightData.getArrivalAirportIATACode());
                                flight.setDepartureDate(flightData.getDepartureDate());
                                flight.setDepartureAirportIATACode(flightData.getDepartureAirportIATACode());
                            }
                            flightRepository.save(flight);
                        }
                );
    }
}
