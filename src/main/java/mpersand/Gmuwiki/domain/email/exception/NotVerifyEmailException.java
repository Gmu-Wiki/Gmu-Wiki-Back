package mpersand.Gmuwiki.domain.email.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class NotVerifyEmailException extends GimuwikiException {

    public NotVerifyEmailException() {
        super(ErrorCode.NOT_VERIFY_EMAIL);
    }
}
