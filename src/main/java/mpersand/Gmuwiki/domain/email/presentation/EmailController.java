package mpersand.Gmuwiki.domain.email.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.email.presentation.dto.request.EmailSendRequest;
import mpersand.Gmuwiki.domain.email.service.EmailCheckService;
import mpersand.Gmuwiki.domain.email.service.EmailSendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailSendService emailSendService;
    private final EmailCheckService emailCheckService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid EmailSendRequest emailSendRequest) {
        emailSendService.execute(emailSendRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> AuthCheck(@Email @RequestParam String email, @RequestParam String authCode) {
        emailCheckService.execute(email, authCode);
        return ResponseEntity.ok().build();
    }
}
