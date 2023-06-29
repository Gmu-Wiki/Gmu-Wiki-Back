package mpersand.Gmuwiki.domain.user.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GmuwikiException;

@Getter
public class UserNotFoundException extends GmuwikiException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
