package mpersand.Gmuwiki.domain.auth.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class ExistEmailException extends GimuwikiException {

    public ExistEmailException() {
        super(ErrorCode.ALREADY_EXIST_EMAIL);
    }
}
