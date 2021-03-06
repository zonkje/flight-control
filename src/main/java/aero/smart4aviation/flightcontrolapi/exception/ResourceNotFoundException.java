package aero.smart4aviation.flightcontrolapi.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, Long id) {
        super(String.format("%s with number : '%s' not found", resource, id));
    }
    public ResourceNotFoundException(String resource, Long id, String departureDate) {
        super(String.format("%s with number : '%s' and departure date: %s not found", resource, id, departureDate));
    }
}
