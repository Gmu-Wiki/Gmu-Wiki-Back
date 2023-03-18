package mpersand.Gmuwiki.domain.email.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class MisMatchAuthCodeException extends GimuwikiException {

    public MisMatchAuthCodeException() {
        super(ErrorCode.MISMATCH_AUTH_CODE);
    }
}
