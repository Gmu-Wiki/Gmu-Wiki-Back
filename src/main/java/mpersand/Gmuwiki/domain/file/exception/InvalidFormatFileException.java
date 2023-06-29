package mpersand.Gmuwiki.domain.file.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GmuwikiException;

@Getter
public class InvalidFormatFileException extends GmuwikiException {

    public InvalidFormatFileException() {
        super(ErrorCode.INVALID_FORMAT_FILE);
    }
}
