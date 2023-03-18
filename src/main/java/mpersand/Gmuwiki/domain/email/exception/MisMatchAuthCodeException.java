package mpersand.Gmuwiki.domain.email.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;

@Getter
public class MisMatchAuthCodeException extends RuntimeException {

    private final ErrorCode errorCode;
    public MisMatchAuthCodeException(String message) {
        super(message);
        this.errorCode = ErrorCode.MISMATCH_AUTH_CODE;
    }
}
