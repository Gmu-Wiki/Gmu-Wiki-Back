package mpersand.Gmuwiki.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.presentation.dto.request.LoginRequest;
import mpersand.Gmuwiki.domain.auth.presentation.dto.request.SignUpRequest;
import mpersand.Gmuwiki.domain.auth.presentation.dto.response.LoginResponse;
import mpersand.Gmuwiki.domain.auth.presentation.dto.response.NewTokenResponse;
import mpersand.Gmuwiki.domain.auth.sevice.TokenReissueService;
import mpersand.Gmuwiki.domain.auth.sevice.UserLoginService;
import mpersand.Gmuwiki.domain.auth.sevice.UserSignUpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserSignUpService userSignUpService;
    private final UserLoginService userLoginService;
    private final TokenReissueService tokenReissueService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        userSignUpService.execute(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = userLoginService.execute(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<NewTokenResponse> reIssueToken(@RequestHeader("RefreshToken") String token) {
        NewTokenResponse reIssueToken = tokenReissueService.execute(token);
        return ResponseEntity.ok(reIssueToken);
    }
}
