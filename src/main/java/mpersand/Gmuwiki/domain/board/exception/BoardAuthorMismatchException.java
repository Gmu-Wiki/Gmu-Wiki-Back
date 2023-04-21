package mpersand.Gmuwiki.domain.board.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class BoardAuthorMismatchException extends GimuwikiException {

    public BoardAuthorMismatchException() {
        super(ErrorCode.MISMATCH_BOARD_AUTHOR);
    }
}
