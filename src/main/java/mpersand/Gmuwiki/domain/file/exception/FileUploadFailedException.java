package mpersand.Gmuwiki.domain.file.exception;

import lombok.Getter;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.GimuwikiException;

@Getter
public class FileUploadFailedException extends GimuwikiException {

    public FileUploadFailedException() {
        super(ErrorCode.FILE_UPLOAD_FAIL);
    }
}
