package exception;

/**
 * 非数值异常类
 * 2017/10/14
 * @author ljy56
 */
public class NotNumberException extends RuntimeException {
    public NotNumberException() {
        super();
    }

    public NotNumberException(String message) {
        super(message);
    }

    public NotNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotNumberException(Throwable cause) {
        super(cause);
    }

    protected NotNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
