package aero.smart4aviation.flightcontrolapi.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource, Long id) {
        super(String.format("%s with ID : '%s' not found", resource, id));
    }
}
