package aero.smart4aviation.flightcontrolapi.repository;

import aero.smart4aviation.flightcontrolapi.model.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long> {
}
