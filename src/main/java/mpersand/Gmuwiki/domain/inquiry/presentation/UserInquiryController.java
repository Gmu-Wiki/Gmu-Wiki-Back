package mpersand.Gmuwiki.domain.inquiry.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.request.InquiryWriteRequest;
import mpersand.Gmuwiki.domain.inquiry.service.CreateInquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/inquiry")
public class UserInquiryController {

    private final CreateInquiryService createInquiryService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestPart("InquiryCreate") @Valid InquiryWriteRequest inquiryWriteRequest, @RequestPart(name = "file", required = false) List<MultipartFile> files) {
        createInquiryService.execute(inquiryWriteRequest, files);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
