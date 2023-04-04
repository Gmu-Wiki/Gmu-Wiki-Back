package mpersand.Gmuwiki.global.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private final String message;
    private final int status;
}
