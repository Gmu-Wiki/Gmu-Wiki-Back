package mpersand.Gmuwiki.global.security.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GmuwikiException;

@Getter
public class TokenNotValidException extends GmuwikiException {

    public TokenNotValidException() {
        super(ErrorCode.TOKEN_NOT_VALID);
    }
}