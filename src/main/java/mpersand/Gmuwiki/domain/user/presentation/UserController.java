package mpersand.Gmuwiki.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.user.presentation.dto.request.GrantAdminRequest;
import mpersand.Gmuwiki.domain.user.service.GrantAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class UserController {

    private final GrantAdminService grantAdminService;

    @PostMapping
    public ResponseEntity<Void> grant(@RequestBody GrantAdminRequest grantAdminRequest) {

        grantAdminService.execute(grantAdminRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}