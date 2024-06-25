package pl.gren.oze_app.exception;

public class Error404 extends RuntimeException {
    public Error404() {
    }

    public Error404(String message) {
        super(message);
    }

    public Error404(String message, Throwable cause) {
        super(message, cause);
    }

    public Error404(Throwable cause) {
        super(cause);
    }

    public Error404(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
