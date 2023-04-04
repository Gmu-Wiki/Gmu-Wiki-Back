package mpersand.Gmuwiki.domain.notice.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeIdResponse;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeListResponse;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeResponse;
import mpersand.Gmuwiki.domain.notice.service.ListNoticeService;
import mpersand.Gmuwiki.domain.notice.service.OneNoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/notice")
@RequiredArgsConstructor
public class UserNoticeController {

    private final ListNoticeService listNoticeService;
    private final OneNoticeService oneNoticeService;

    @GetMapping("/list")
    public ResponseEntity<NoticeListResponse> findAll(){
        NoticeListResponse list = listNoticeService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeIdResponse> findOne(@PathVariable("id") Long id){
        NoticeIdResponse oneFindById = oneNoticeService.execute(id);
        return new ResponseEntity<>(oneFindById,HttpStatus.OK);
    }
}
