package mpersand.Gmuwiki.domain.board.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class NotMyBoardException extends GimuwikiException {

    public NotMyBoardException() {
        super(ErrorCode.NOT_MY_BOARD);
    }
}
