package mpersand.Gmuwiki.global.security.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class RefreshTokenExpirationException extends GimuwikiException {

    public RefreshTokenExpirationException() {
        super(ErrorCode.TOKEN_IS_EXPIRED);
    }
}