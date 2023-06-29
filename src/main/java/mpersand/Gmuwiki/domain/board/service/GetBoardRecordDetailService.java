package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.BoardRecord;
import mpersand.Gmuwiki.domain.board.exception.BoardRecordNotFoundException;
import mpersand.Gmuwiki.domain.board.presentation.dto.response.DetailBoardResponse;
import mpersand.Gmuwiki.domain.board.repository.BoardRecordRepository;
import mpersand.Gmuwiki.global.annotation.ReadOnlyService;

@ReadOnlyService
@RequiredArgsConstructor
public class GetBoardRecordDetailService {

    private final BoardRecordRepository boardRecordRepository;

    public DetailBoardResponse execute(Long id) {

        BoardRecord boardRecord = boardRecordRepository.findById(id)
                .orElseThrow(() -> new BoardRecordNotFoundException());

        DetailBoardResponse detailBoardResponse = DetailBoardResponse.builder()
                .title(boardRecord.getTitle())
                .content(boardRecord.getContent())
                .createdDate(boardRecord.getCreatedDate())
                .editedDate(boardRecord.getEditedDate())
                .build();

        return detailBoardResponse;
    }
}
