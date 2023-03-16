package mpersand.Gmuwiki.domain.email.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.email.presentation.dto.request.EmailSendRequest;
import mpersand.Gmuwiki.domain.email.service.EmailSendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailSendService emailSendService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid EmailSendRequest emailSendRequest) {
        emailSendService.execute(emailSendRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
