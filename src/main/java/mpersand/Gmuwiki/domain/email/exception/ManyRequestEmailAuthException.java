package mpersand.Gmuwiki.domain.email.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class ManyRequestEmailAuthException extends GimuwikiException {

    public ManyRequestEmailAuthException() {
        super(ErrorCode.MANY_REQUEST_EMAIL_AUTH);
    }
}
