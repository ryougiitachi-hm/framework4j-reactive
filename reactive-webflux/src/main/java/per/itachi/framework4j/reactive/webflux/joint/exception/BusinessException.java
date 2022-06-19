package per.itachi.framework4j.reactive.webflux.joint.exception;

public class BusinessException extends RuntimeException{

    public BusinessException() {
    super();
}

    public BusinessException(String message) {
        super(message);
    }
}
