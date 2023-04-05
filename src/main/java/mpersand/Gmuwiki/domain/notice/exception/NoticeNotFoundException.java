package mpersand.Gmuwiki.domain.notice.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class NoticeNotFoundException extends GimuwikiException {
    public NoticeNotFoundException(String 게시글을_찾을수_없습니다) {
        super(ErrorCode.NOTICE_NOT_FOUND);
    }
}
