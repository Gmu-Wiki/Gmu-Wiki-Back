package mpersand.Gmuwiki.domain.auth.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;

@Getter
public class ExistEmailException extends RuntimeException {

    private final ErrorCode errorCode;
    public ExistEmailException(String message) {
        super(message);
        this.errorCode = ErrorCode.ALREADY_EXIST_EMAIL;
    }
}
