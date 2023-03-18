package mpersand.Gmuwiki.domain.email.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;

@Getter
public class EmailSendFailedException extends RuntimeException {

    private final ErrorCode errorCode;
    public EmailSendFailedException(String message) {
        super(message);
        this.errorCode = ErrorCode.EMAIL_SEND_FAIL;
    }
}

