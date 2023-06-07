package mpersand.Gmuwiki.domain.auth.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class RoleNotExistException extends GimuwikiException {

    public RoleNotExistException() {
        super(ErrorCode.ROLE_NOT_EXIST);
    }
}