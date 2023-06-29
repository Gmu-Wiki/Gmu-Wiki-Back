package mpersand.Gmuwiki.domain.board.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GmuwikiException;

@Getter
public class BoardAuthorMismatchException extends GmuwikiException {

    public BoardAuthorMismatchException() {
        super(ErrorCode.MISMATCH_BOARD_AUTHOR);
    }
}
