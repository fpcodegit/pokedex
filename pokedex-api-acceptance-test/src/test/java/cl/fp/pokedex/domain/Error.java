package cl.fp.pokedex.domain;

import java.time.Instant;
import java.util.Objects;

public class Error {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String exception;
    private String message;
    private String path;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error error1 = (Error) o;
        return Objects.equals(timestamp, error1.timestamp) &&
                Objects.equals(status, error1.status) &&
                Objects.equals(error, error1.error) &&
                Objects.equals(exception, error1.exception) &&
                Objects.equals(message, error1.message) &&
                Objects.equals(path, error1.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, status, error, exception, message, path);
    }

    @Override
    public String toString() {
        return "Error{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", exception='" + exception + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
