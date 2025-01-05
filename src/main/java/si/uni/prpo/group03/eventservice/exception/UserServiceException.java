package si.uni.prpo.group03.eventservice.exception;

public class UserServiceException extends RuntimeException {
    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
