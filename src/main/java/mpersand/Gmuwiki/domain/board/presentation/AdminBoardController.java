package mpersand.Gmuwiki.domain.board.presentation;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.enums.BoardType;
import mpersand.Gmuwiki.domain.board.presentation.dto.request.CreateBoardRequest;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.DetailBoardResponse;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.ListBoardResponse;
import mpersand.Gmuwiki.domain.board.service.CreateBoardService;
import mpersand.Gmuwiki.domain.board.service.ListBoardService;
import mpersand.Gmuwiki.domain.board.service.OneBoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/board")
public class AdminBoardController {

    private final CreateBoardService createBoardService;

    private final ListBoardService listBoardService;

    private final OneBoardService oneBoardService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateBoardRequest createBoardRequest) {
        createBoardService.execute(createBoardRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListBoardResponse> findAll(@RequestParam BoardType boardType) {
        var list = listBoardService.execute(boardType);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailBoardResponse> findOne(@PathVariable("id") Long id) {
        DetailBoardResponse detailBoardResponse = oneBoardService.execute(id);
        return new ResponseEntity<>(detailBoardResponse, HttpStatus.OK);
    }
}
