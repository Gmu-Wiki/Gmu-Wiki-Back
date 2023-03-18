package mpersand.Gmuwiki.domain.notice.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust.CreateNoticeRequest;
import mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust.ModifyNoticeRequest;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeResponse;
import mpersand.Gmuwiki.domain.notice.service.CreateNoticeService;
import mpersand.Gmuwiki.domain.notice.service.ListNoticeService;
import mpersand.Gmuwiki.domain.notice.service.ModifyNoticeService;
import mpersand.Gmuwiki.domain.notice.service.RemoveNoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final CreateNoticeService createNoticeService;
    private final ModifyNoticeService modifyNoticeService;
    private final RemoveNoticeService removeNoticeService;
    private final ListNoticeService listNoticeService;


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateNoticeRequest createNoticeRequest){
        createNoticeService.execute(createNoticeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> modify(@PathVariable Long id, @RequestBody @Valid ModifyNoticeRequest modifyNoticeRequest){
        modifyNoticeService.execute(id, modifyNoticeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        removeNoticeService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<NoticeResponse>> findALl(){
        List<NoticeResponse> list = listNoticeService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }





}
