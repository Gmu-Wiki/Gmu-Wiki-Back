package mpersand.Gmuwiki.domain.auth.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class BlackListAlreadyExistException extends GimuwikiException {
    public BlackListAlreadyExistException() {
        super(ErrorCode.BLACKLIST_ALREADY_EXIST);
    }
}
