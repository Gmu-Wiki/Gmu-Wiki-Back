package mpersand.Gmuwiki.domain.email.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;

@Getter
public class NotVerifyEmailException extends RuntimeException {

    private final ErrorCode errorCode;
    public NotVerifyEmailException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_VERIFY_EMAIL;
    }
}
