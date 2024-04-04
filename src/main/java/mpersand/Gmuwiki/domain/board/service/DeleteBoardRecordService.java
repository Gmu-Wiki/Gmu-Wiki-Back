package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.BoardRecord;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.BoardRecordUtil;

@RequiredArgsConstructor
@RollbackService
public class DeleteBoardRecordService {

    public final BoardRecordUtil boardRecordUtil;

    public void execute(Long id) {

        BoardRecord boardRecord = boardRecordUtil.findBoardRecordById(id);

        boardRecordUtil.deleteBoardRecord(boardRecord);
    }
}
