package mpersand.Gmuwiki.domain.board.exception;

import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

public class ExistTitleException extends GimuwikiException {

    public ExistTitleException() {
        super(ErrorCode.ALREADY_EXIST_TITLE);
    }
}
