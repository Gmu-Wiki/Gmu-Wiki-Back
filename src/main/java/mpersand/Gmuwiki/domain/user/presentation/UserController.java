package mpersand.Gmuwiki.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.user.presentation.dto.request.ChangePasswordRequest;
import mpersand.Gmuwiki.domain.user.service.ChangePasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final ChangePasswordService changePasswordService;

    @PatchMapping("/password")
    public ResponseEntity<Void> ChangePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        changePasswordService.execute(changePasswordRequest);
        return ResponseEntity.noContent().build();
    }
}
