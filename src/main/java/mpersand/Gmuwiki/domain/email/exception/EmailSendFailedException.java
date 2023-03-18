package mpersand.Gmuwiki.domain.email.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class EmailSendFailedException extends GimuwikiException {

    public EmailSendFailedException() {
        super(ErrorCode.EMAIL_SEND_FAIL);
    }
}

