package aero.smart4aviation.flightcontrolapi.repository;

import aero.smart4aviation.flightcontrolapi.model.Baggage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaggageRepository extends CrudRepository<Baggage, Long> {
}
