package si.uni.prpo.group03.eventservice.exception;

public class VenueNotFoundException extends RuntimeException {
    public VenueNotFoundException(String message) {
        super(message);
    }
}
