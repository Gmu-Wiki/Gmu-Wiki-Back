package mpersand.Gmuwiki.domain.board.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.enums.BoardType;
import mpersand.Gmuwiki.domain.board.presentation.dto.request.CreateBoardRequest;
import mpersand.Gmuwiki.domain.board.presentation.dto.request.EditBoardRequest;
import mpersand.Gmuwiki.domain.board.presentation.dto.request.SearchBoardTitleRequest;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.*;
import mpersand.Gmuwiki.domain.board.service.*;
import mpersand.Gmuwiki.global.annotation.RestRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestRequestService("/board")
public class BoardController {

    private final CreateBoardService createBoardService;

    private final ListBoardService listBoardService;

    private final GetBoardDetailService getBoardDetailService;

    private final DeleteBoardService deleteBoardService;

    private final EditBoardService editBoardService;

    private final ListBoardRecordService listBoardRecordService;

    private final GetBoardRecordDetailService getBoardRecordDetailService;

    private final ListSearchBoardTitleService listSearchBoardTitleService;

    private final ListRecentEditBoardService listRecentEditBoardService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateBoardRequest createBoardRequest) {
        createBoardService.execute(createBoardRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<ListSearchBoardResponse> searchTitle(@RequestParam("title") SearchBoardTitleRequest searchBoardTitleRequest) {
        var list = listSearchBoardTitleService.execute(searchBoardTitleRequest);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<ListResentBoardResponse> recentEditBoardTitle() {
        var list = listRecentEditBoardService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListBoardResponse> findTypeAll(@RequestParam BoardType boardType) {
        var list = listBoardService.execute(boardType);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}/record")
    public ResponseEntity<ListBoardRecordResponse> findRecordAll(@PathVariable Long id) {
        var list = listBoardRecordService.execute(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}/record/detail")
    public ResponseEntity<DetailBoardResponse> findRecordDetailOne(@PathVariable Long id) {
        DetailBoardResponse oneFindById = getBoardRecordDetailService.execute(id);
        return new ResponseEntity<>(oneFindById, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailBoardResponse> findDetailOne(@PathVariable Long id) {
        DetailBoardResponse oneFindById = getBoardDetailService.execute(id);
        return new ResponseEntity<>(oneFindById, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteBoardService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody @Valid EditBoardRequest editBoardRequest) {
        editBoardService.execute(id, editBoardRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
