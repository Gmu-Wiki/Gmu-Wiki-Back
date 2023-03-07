package mpersand.Gmuwiki.domain.auth.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;
    public UserNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.USER_NOT_FOUND;
    }
}
