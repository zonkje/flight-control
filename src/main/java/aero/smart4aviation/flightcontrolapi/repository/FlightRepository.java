package aero.smart4aviation.flightcontrolapi.repository;

import aero.smart4aviation.flightcontrolapi.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

    Optional<Flight> findByFlightNumber(Long flightNumber);
    Collection<Flight> findAllByArrivalAirportIATACode(String airportIATACode);
    Collection<Flight> findAllByDepartureAirportIATACode(String airportIATACode);

}
