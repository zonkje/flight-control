package aero.smart4aviation.flightcontrolapi.repository;

import aero.smart4aviation.flightcontrolapi.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {

}
