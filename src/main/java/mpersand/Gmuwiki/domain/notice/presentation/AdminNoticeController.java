package mpersand.Gmuwiki.domain.notice.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust.CreateNoticeRequest;
import mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust.ModifyNoticeRequest;
import mpersand.Gmuwiki.domain.notice.service.CreateNoticeService;
import mpersand.Gmuwiki.domain.notice.service.ModifyNoticeService;
import mpersand.Gmuwiki.domain.notice.service.RemoveNoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final CreateNoticeService createNoticeService;
    private final ModifyNoticeService modifyNoticeService;
    private final RemoveNoticeService removeNoticeService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateNoticeRequest createNoticeRequest){
        createNoticeService.create(createNoticeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> modify(@PathVariable Long id, @RequestBody @Valid ModifyNoticeRequest modifyNoticeRequest){
        modifyNoticeService.modify(id, modifyNoticeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        removeNoticeService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
