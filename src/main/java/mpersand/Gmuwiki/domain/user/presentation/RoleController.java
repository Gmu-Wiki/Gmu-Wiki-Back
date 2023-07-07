package mpersand.Gmuwiki.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.user.presentation.dto.request.RoleChangeRequest;
import mpersand.Gmuwiki.domain.user.service.AdminRoleChangeService;
import mpersand.Gmuwiki.domain.user.service.UserRoleChangeService;
import mpersand.Gmuwiki.global.annotation.RestRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestRequestService("/admin/role")
public class RoleController {

    private final AdminRoleChangeService adminRoleChangeService;

    private final UserRoleChangeService userRoleChangeService;

    @PatchMapping("/grant")
    public ResponseEntity<Void> grant(@RequestBody RoleChangeRequest roleChangeRequest) {

        adminRoleChangeService.execute(roleChangeRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/revoke")
    public ResponseEntity<Void> revoke(@RequestBody RoleChangeRequest roleChangeRequest) {

        userRoleChangeService.execute(roleChangeRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}