package mpersand.Gmuwiki.domain.notice.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GmuwikiException;

@Getter
public class NoticeNotFoundException extends GmuwikiException {
    public NoticeNotFoundException() {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}
