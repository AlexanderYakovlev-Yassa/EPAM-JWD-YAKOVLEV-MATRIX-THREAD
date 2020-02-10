package by.epam.jwd.yakovlev.matrix.exception;

public class CellAccessException extends Exception {

    public CellAccessException() {
    }

    public CellAccessException(String message) {
        super(message);
    }

    public CellAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public CellAccessException(Throwable cause) {
        super(cause);
    }
}
