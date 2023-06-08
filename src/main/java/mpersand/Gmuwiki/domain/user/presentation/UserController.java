package mpersand.Gmuwiki.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.user.presentation.dto.request.GrantAdminRequest;
import mpersand.Gmuwiki.domain.user.service.GrantAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class UserController {

    private final GrantAdminService grantAdminService;

    @PatchMapping
    public ResponseEntity<Void> grant(@RequestBody GrantAdminRequest grantAdminRequest) {

        grantAdminService.execute(grantAdminRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}