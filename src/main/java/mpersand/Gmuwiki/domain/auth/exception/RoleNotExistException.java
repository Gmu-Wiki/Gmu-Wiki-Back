package mpersand.Gmuwiki.domain.auth.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GmuwikiException;

@Getter
public class RoleNotExistException extends GmuwikiException {

    public RoleNotExistException() {
        super(ErrorCode.ROLE_NOT_EXIST);
    }
}