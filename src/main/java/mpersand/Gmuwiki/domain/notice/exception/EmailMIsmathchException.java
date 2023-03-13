package mpersand.Gmuwiki.domain.notice.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;

@Getter
public class EmailMIsmathchException extends RuntimeException {
    private final ErrorCode errorCode;

    public EmailMIsmathchException(String message){
        super(message);
        this.errorCode = ErrorCode.EMAIL_MISMATCH;
    }
}
