package mpersand.Gmuwiki.domain.notice.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;

@Getter
public class NoticeNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public NoticeNotFoundException(String message){
        super(message);
        this.errorCode = ErrorCode.NOTICE_NOT_FOUND;
    }
}
