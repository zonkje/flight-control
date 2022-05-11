package aero.smart4aviation.flightcontrolapi.repository;

import aero.smart4aviation.flightcontrolapi.model.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, Long> {
}
