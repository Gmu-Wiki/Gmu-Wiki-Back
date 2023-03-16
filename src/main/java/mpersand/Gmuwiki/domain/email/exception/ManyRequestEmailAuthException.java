package mpersand.Gmuwiki.domain.email.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;

@Getter
public class ManyRequestEmailAuthException extends RuntimeException {

    private final ErrorCode errorCode;
    public ManyRequestEmailAuthException(String message) {
        super(message);
        this.errorCode = ErrorCode.MANY_REQUEST_EMAIL_AUTH;
    }
}
