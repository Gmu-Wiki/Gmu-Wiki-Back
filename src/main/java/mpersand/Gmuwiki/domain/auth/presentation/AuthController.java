package mpersand.Gmuwiki.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.presentation.dto.request.SignUpRequest;
import mpersand.Gmuwiki.domain.auth.sevice.UserSignUpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserSignUpService userSignUpService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userSignUpService.execute(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
