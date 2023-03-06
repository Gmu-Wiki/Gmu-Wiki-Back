package mpersand.Gmuwiki.global.security.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;

@Getter
public class TokenNotValidException extends RuntimeException {
    private final ErrorCode errorCode;
    public TokenNotValidException(String message) {
        super(message);
        this.errorCode = ErrorCode.TOKEN_NOT_VALID;
    }
}