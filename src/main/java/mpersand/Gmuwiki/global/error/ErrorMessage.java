package mpersand.Gmuwiki.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage {
    private final String message;
    private final int status;

    public ErrorMessage(ErrorCode errorCode){
        this(errorCode.getMessage(), errorCode.getStatus());
    }
}
