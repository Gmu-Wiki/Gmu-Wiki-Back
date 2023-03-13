package mpersand.Gmuwiki.domain.notice.exception;

import mpersand.Gmuwiki.global.error.ErrorCode;

public class NoticeNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public NoticeNotFoundException(String message){
        super(message);
        this.errorCode = ErrorCode.POST_NOT_FOUND;
    }
}
