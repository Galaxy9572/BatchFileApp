package exception;

/**
 * 列数异常类
 * 2017/10/14
 * @author ljy56
 */
public class ColumnException extends RuntimeException {
    public ColumnException() {
        super();
    }

    public ColumnException(String message) {
        super(message);
    }

    public ColumnException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColumnException(Throwable cause) {
        super(cause);
    }

    protected ColumnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
