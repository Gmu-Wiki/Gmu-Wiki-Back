package mpersand.Gmuwiki.domain.inquiry.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.request.InquirySendRequest;
import mpersand.Gmuwiki.domain.inquiry.service.InquirySendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/inquiry")
public class UserInquiryController {

    private final InquirySendService inquirySendService;

    @PostMapping
    public ResponseEntity<Void> send(@RequestBody @Valid InquirySendRequest inquirySendRequest) {
        inquirySendService.execute(inquirySendRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
