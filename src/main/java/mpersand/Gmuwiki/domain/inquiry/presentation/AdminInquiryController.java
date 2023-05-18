package mpersand.Gmuwiki.domain.inquiry.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.request.InquirySendRequest;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.response.DetailInquiryResponse;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.response.ListInquiryResponse;
import mpersand.Gmuwiki.domain.inquiry.service.GetInquiryDetailService;
import mpersand.Gmuwiki.domain.inquiry.service.InquirySendService;
import mpersand.Gmuwiki.domain.inquiry.service.ListInquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/inquiry")
public class AdminInquiryController {

    private final InquirySendService inquirySendService;

    private final ListInquiryService listInquiryService;

    private final GetInquiryDetailService getInquiryDetailService;

    @PostMapping
    public ResponseEntity<Void> send(@RequestBody @Valid InquirySendRequest inquirySendRequest) {
        inquirySendService.execute(inquirySendRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListInquiryResponse> findAll() {
        var list = listInquiryService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailInquiryResponse> findDetailOne(@PathVariable Long id) {
        DetailInquiryResponse oneFindById = getInquiryDetailService.execute(id);
        return new ResponseEntity<>(oneFindById, HttpStatus.OK);
    }
}