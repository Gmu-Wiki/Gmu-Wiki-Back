package mpersand.Gmuwiki.domain.auth.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class MisMatchPasswordException extends GimuwikiException {

    public MisMatchPasswordException() {
        super(ErrorCode.MISMATCH_PASSWORD);
    }
}
