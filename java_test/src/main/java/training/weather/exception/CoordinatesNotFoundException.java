package training.weather.exception;

public class CoordinatesNotFoundException extends RuntimeException {
    public CoordinatesNotFoundException(String message) {
        super(message);
    }
}
