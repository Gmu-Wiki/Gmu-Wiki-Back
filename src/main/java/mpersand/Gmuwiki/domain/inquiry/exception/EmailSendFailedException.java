package mpersand.Gmuwiki.domain.inquiry.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GmuwikiException;

@Getter
public class EmailSendFailedException extends GmuwikiException {

    public EmailSendFailedException() {
        super(ErrorCode.EMAIL_SEND_FAIL);
    }
}

