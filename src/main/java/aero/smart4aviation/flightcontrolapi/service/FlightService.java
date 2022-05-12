package aero.smart4aviation.flightcontrolapi.service;

import aero.smart4aviation.flightcontrolapi.exception.ResourceNotFoundException;
import aero.smart4aviation.flightcontrolapi.exception.UnrecognizedWeightUnitException;
import aero.smart4aviation.flightcontrolapi.model.Flight;
import aero.smart4aviation.flightcontrolapi.model.Freight;
import aero.smart4aviation.flightcontrolapi.repository.BaggageRepository;
import aero.smart4aviation.flightcontrolapi.repository.CargoRepository;
import aero.smart4aviation.flightcontrolapi.repository.FlightRepository;
import aero.smart4aviation.flightcontrolapi.response.AirportFlightsDataResponse;
import aero.smart4aviation.flightcontrolapi.response.FlightWeightDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService {

    private final FlightRepository flightRepository;
    private final BaggageRepository baggageRepository;
    private final CargoRepository cargoRepository;

    public FlightWeightDataResponse getFlightWeight(Long flightId, String flightDate) {
        Flight flight = flightRepository.findByFlightNumber(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight", flightId));
        OffsetDateTime flightDepartureDateTime = flight.getDepartureDate();

        if (!checkInputDate(flightDate, flightDepartureDateTime)) {
            throw new ResourceNotFoundException("Flight", flightId, flightDate);
        }

        String weightUnit = "kg";
        Double flightCargoWeightKgs = calculateFreightWeight(flight.getCargo());
        Double flightBaggageWeightKgs = calculateFreightWeight(flight.getBaggage());

        return FlightWeightDataResponse.builder()
                .weightUnit(weightUnit)
                .cargoWeight(flightCargoWeightKgs)
                .baggageWeight(flightBaggageWeightKgs)
                .totalWeight(flightCargoWeightKgs + flightBaggageWeightKgs)
                .build();
    }

    public AirportFlightsDataResponse getAirportFlightsData(String airportIATACode, String flightDate) {
        Collection<Flight> arrivals = flightRepository.findAllByArrivalAirportIATACode(airportIATACode);
        Collection<Flight> departures = flightRepository.findAllByDepartureAirportIATACode(airportIATACode);
        Collection<Flight> departuresOnDateTime = departures.stream()
                .filter(flight -> checkInputDate(flightDate, flight.getDepartureDate()))
                .collect(Collectors.toCollection(ArrayList::new));

        Integer arrivingBaggagePieces = sumFlightsBaggagePieces(arrivals);
        Integer departingBaggagePieces = sumFlightsBaggagePieces(departuresOnDateTime);

        return AirportFlightsDataResponse.builder()
                .departuresNumber(departuresOnDateTime.size())
                .arrivalsNumber(arrivals.size())
                .arrivingBaggagePieces(arrivingBaggagePieces)
                .departingBaggagePieces(departingBaggagePieces)
                .build();
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

    private boolean checkInputDate(String inputFlightDate, OffsetDateTime flightDate) {
        ZoneOffset offset = flightDate.getOffset();

        LocalDateTime inputDateTime = LocalDateTime.parse(inputFlightDate);
        OffsetDateTime inputOffsetDateTime = inputDateTime.atOffset(offset);

        return flightDate.withOffsetSameInstant(ZoneOffset.of("-02:00")).toLocalDateTime()
                .equals(inputOffsetDateTime.toLocalDateTime());
    }

    private Integer sumFlightsBaggagePieces(Collection<Flight> flights) {
        return flights.stream()
                .flatMapToInt(flight -> flight.getBaggage().stream().mapToInt(Freight::getPieces))
                .sum();
    }

    private Double calculateFreightWeight(Collection<? extends Freight> freights) {
        final Double poundToKgRate = 0.45359237;
        double flightFreightWeightKgs = 0.0d;

        for (Freight freight : freights) {
            String unit = freight.getWeightUnit();
            if (unit.equals("kg")) {
                flightFreightWeightKgs += freight.getWeight() * freight.getPieces();
            } else if (unit.equals("lb")) {
                flightFreightWeightKgs += (freight.getWeight() * poundToKgRate) * freight.getPieces();
            } else {
                throw new UnrecognizedWeightUnitException(unit);
            }
        }
        return flightFreightWeightKgs;
    }
}
