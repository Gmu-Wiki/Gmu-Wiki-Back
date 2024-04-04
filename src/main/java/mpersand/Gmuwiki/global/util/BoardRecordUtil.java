package mpersand.Gmuwiki.global.util;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.BoardRecord;
import mpersand.Gmuwiki.domain.board.exception.BoardRecordNotFoundException;
import mpersand.Gmuwiki.domain.board.repository.BoardRecordRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardRecordUtil {

    public final BoardRecordRepository boardRecordRepository;

    public BoardRecord findBoardRecordById(Long id) {

        return boardRecordRepository.findById(id)
                .orElseThrow(BoardRecordNotFoundException::new);
    }

    public void deleteBoardRecord(BoardRecord boardRecord) {

        boardRecordRepository.delete(boardRecord);
    }
}
