package mpersand.Gmuwiki.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.auth.presentation.data.dto.LoginDto;
import mpersand.Gmuwiki.domain.auth.presentation.data.request.LoginRequest;
import mpersand.Gmuwiki.domain.auth.presentation.data.response.LoginResponse;
import mpersand.Gmuwiki.domain.auth.presentation.data.response.NewTokenResponse;
import mpersand.Gmuwiki.domain.auth.sevice.TokenReissueService;
import mpersand.Gmuwiki.domain.auth.sevice.UserLoginService;
import mpersand.Gmuwiki.domain.auth.sevice.UserLogoutService;
import mpersand.Gmuwiki.domain.auth.util.AuthConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserLoginService userLoginService;

    private final UserLogoutService userLogoutService;

    private final TokenReissueService tokenReissueService;

    private final AuthConverter authConverter;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws IOException {

        LoginDto loginDto = authConverter.toDto(loginRequest);

        LoginResponse loginResponse = userLoginService.execute(loginDto);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<NewTokenResponse> reIssueToken(@RequestHeader("Refresh-Token") String refreshToken) {

        NewTokenResponse newTokenResponse = tokenReissueService.execute(refreshToken);

        return new ResponseEntity<>(newTokenResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(){

        userLogoutService.execute();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
