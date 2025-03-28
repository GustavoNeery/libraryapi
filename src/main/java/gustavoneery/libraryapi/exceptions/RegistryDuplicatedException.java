package gustavoneery.libraryapi.exceptions;

public class RegistryDuplicatedException extends RuntimeException {
    public RegistryDuplicatedException(String message) {
        super(message);
    }
}
