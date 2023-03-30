package mpersand.Gmuwiki.domain.notice.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeResponse;
import mpersand.Gmuwiki.domain.notice.service.ListNoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final ListNoticeService listNoticeService;

    @GetMapping("/list")
    public ResponseEntity<List<NoticeResponse>> findAll(){
        List<NoticeResponse> list = listNoticeService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
