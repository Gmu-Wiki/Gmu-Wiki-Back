package mpersand.Gmuwiki.domain.auth.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;

@Getter
public class MisMatchPasswordException extends RuntimeException {
    private final ErrorCode errorCode;
    public MisMatchPasswordException(String message) {
        super(message);
        this.errorCode = ErrorCode.MISMATCH_PASSWORD;
    }
}
