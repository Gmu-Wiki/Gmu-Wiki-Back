package mpersand.Gmuwiki.domain.notice.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeIdResponse;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeResponse;
import mpersand.Gmuwiki.domain.notice.service.ListNoticeService;
import mpersand.Gmuwiki.domain.notice.service.OneNoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member/notice")
@RequiredArgsConstructor
public class MemberNoticeController {

    private final ListNoticeService listNoticeService;
    private final OneNoticeService oneNoticeService;

    @GetMapping("/list")
    public ResponseEntity<List<NoticeResponse>> findAll(){
        List<NoticeResponse> list = listNoticeService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeIdResponse> findOne(@PathVariable("id") Long id){
        NoticeIdResponse oneFindById = oneNoticeService.execute(id);
        return new ResponseEntity<>(oneFindById,HttpStatus.OK);
    }

}
