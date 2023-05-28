package mpersand.Gmuwiki.domain.inquiry.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.request.InquirySendRequest;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.request.InquiryWriteRequest;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.response.DetailInquiryResponse;
import mpersand.Gmuwiki.domain.inquiry.presentation.dto.response.ListInquiryResponse;
import mpersand.Gmuwiki.domain.inquiry.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/inquiry")
public class AdminInquiryController {

    private final CreateInquiryService createInquiryService;

    private final ListInquiryService listInquiryService;

    private final GetInquiryDetailService getInquiryDetailService;

    private final InquiryApproveService inquiryApproveService;

    private final InquiryRefusalService inquiryRefusalService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid InquiryWriteRequest inquiryWriteRequest) {
        createInquiryService.execute(inquiryWriteRequest);
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

    @PostMapping("/approve/{id}")
    public ResponseEntity<Void> approveSend(@PathVariable Long id) {
        inquiryApproveService.execute(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/refusal/{id}")
    public ResponseEntity<Void> refusalSend(@PathVariable Long id, @RequestBody @Valid InquirySendRequest inquirySendRequest) {
        inquiryRefusalService.execute(id, inquirySendRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}