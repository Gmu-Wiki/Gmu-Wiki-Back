package mpersand.Gmuwiki.domain.file.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GmuwikiException;

@Getter
public class NotAllowedFileException extends GmuwikiException {

    public NotAllowedFileException() {
        super(ErrorCode.NOT_ALLOWED_FILE);
    }
}
