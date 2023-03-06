package mpersand.Gmuwiki.global.security.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;

@Getter
public class TokenExpirationException extends RuntimeException {
    private final ErrorCode errorCode;
    public TokenExpirationException(String message) {
        super(message);
        this.errorCode = ErrorCode.TOKEN_IS_EXPIRED;
    }
}

