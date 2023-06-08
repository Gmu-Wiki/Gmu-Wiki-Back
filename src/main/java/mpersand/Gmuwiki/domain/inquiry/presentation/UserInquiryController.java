package mpersand.Gmuwiki.domain.inquiry.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.request.InquiryWriteRequest;
import mpersand.Gmuwiki.domain.inquiry.service.CreateInquiryService;
import mpersand.Gmuwiki.global.annotation.RestRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestRequestService("/user/inquiry")
public class UserInquiryController {

    private final CreateInquiryService createInquiryService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid InquiryWriteRequest inquiryWriteRequest) {
        createInquiryService.execute(inquiryWriteRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
