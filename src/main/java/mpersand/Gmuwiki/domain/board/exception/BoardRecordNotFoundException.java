package mpersand.Gmuwiki.domain.board.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class BoardRecordNotFoundException extends GimuwikiException {

    public BoardRecordNotFoundException() {
        super(ErrorCode.BOARD_RECORD_NOT_FOUND);
    }
}
