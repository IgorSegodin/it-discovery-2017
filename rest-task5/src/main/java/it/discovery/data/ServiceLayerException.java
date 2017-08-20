package it.discovery.data;

/**
 * @author isegodin
 */
public class ServiceLayerException extends RuntimeException {

    private int httpCode;

    public ServiceLayerException(int httpCode) {
        this.httpCode = httpCode;
    }

    public ServiceLayerException(String message, int httpCode) {
        super(message);
        this.httpCode = httpCode;
    }

    public ServiceLayerException(String message, Throwable cause, int httpCode) {
        super(message, cause);
        this.httpCode = httpCode;
    }

    public ServiceLayerException(Throwable cause, int httpCode) {
        super(cause);
        this.httpCode = httpCode;
    }

    public ServiceLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int httpCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
