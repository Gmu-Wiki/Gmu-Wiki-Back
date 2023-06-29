package mpersand.Gmuwiki.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.user.presentation.dto.request.RoleChangeRequest;
import mpersand.Gmuwiki.domain.user.service.AdminChangeService;
import mpersand.Gmuwiki.domain.user.service.UserChangeService;
import mpersand.Gmuwiki.global.annotation.RestRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestRequestService("/admin/user")
public class UserController {

    private final AdminChangeService adminChangeService;

    private final UserChangeService userChangeService;

    @PatchMapping("/grant")
    public ResponseEntity<Void> grant(@RequestBody RoleChangeRequest roleChangeRequest) {

        adminChangeService.execute(roleChangeRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/revoke")
    public ResponseEntity<Void> revoke(@RequestBody RoleChangeRequest roleChangeRequest) {

        userChangeService.execute(roleChangeRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}