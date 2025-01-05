package si.uni.prpo.group03.eventservice.exception;

public class EventNotFoundException extends RuntimeException {
    
    public EventNotFoundException(String message) {
        super(message);
    }
}
