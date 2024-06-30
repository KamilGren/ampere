package pl.gren.oze_app.exception;

public class ApiError400 extends RuntimeException {
    public ApiError400() {
    }

    public ApiError400(String message) {
        super(message);
    }

    public ApiError400(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiError400(Throwable cause) {
        super(cause);
    }
}
