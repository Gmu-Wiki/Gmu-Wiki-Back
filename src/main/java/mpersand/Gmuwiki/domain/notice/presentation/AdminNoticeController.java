package mpersand.Gmuwiki.domain.notice.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.presentation.dto.request.CreateNoticeRequest;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeDetailResponse;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeListResponse;
import mpersand.Gmuwiki.domain.notice.service.CreateNoticeService;
import mpersand.Gmuwiki.domain.notice.service.ListNoticeService;
import mpersand.Gmuwiki.domain.notice.service.OneNoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final ListNoticeService listNoticeService;
    private final OneNoticeService oneNoticeService;
    private final CreateNoticeService createNoticeService;

    @GetMapping
    public ResponseEntity<NoticeListResponse> findAll(){
        var list = listNoticeService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeDetailResponse> findOne(@PathVariable("id") Long id){
        NoticeDetailResponse oneFindById = oneNoticeService.execute(id);
        return new ResponseEntity<>(oneFindById,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateNoticeRequest createNoticeRequest){
        createNoticeService.execute(createNoticeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
