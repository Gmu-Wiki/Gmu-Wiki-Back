package mpersand.Gmuwiki.domain.notice.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.presentation.dto.request.CreateNoticeRequest;
import mpersand.Gmuwiki.domain.notice.presentation.dto.request.EditNoticeRequest;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeDetailResponse;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeListResponse;
import mpersand.Gmuwiki.domain.notice.service.*;
import mpersand.Gmuwiki.global.annotation.RestRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestRequestService("/admin/notice")
public class AdminNoticeController {

    private final ListNoticeService listNoticeService;
    private final OneNoticeService oneNoticeService;
    private final CreateNoticeService createNoticeService;
    private final EditNoticeService editNoticeService;
    private final DeleteNoticeService deleteNoticeService;

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

    @PatchMapping("/{id}")
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody @Valid EditNoticeRequest editNoticeRequest){
        editNoticeService.execute(id, editNoticeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        deleteNoticeService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
