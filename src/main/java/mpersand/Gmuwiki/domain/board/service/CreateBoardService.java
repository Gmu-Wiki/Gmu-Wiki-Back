package mpersand.Gmuwiki.domain.board.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.board.entity.Board;
import mpersand.Gmuwiki.domain.board.enums.BoardType;
import mpersand.Gmuwiki.domain.board.exception.ExistTitleException;
import mpersand.Gmuwiki.domain.board.presentation.dto.request.CreateBoardRequest;
import mpersand.Gmuwiki.domain.board.repository.BoardRepository;
import mpersand.Gmuwiki.domain.file.entity.BoardFile;
import mpersand.Gmuwiki.domain.file.repository.BoardFileRepository;
import mpersand.Gmuwiki.domain.file.service.BoardFileService;
import mpersand.Gmuwiki.domain.user.entity.User;
import mpersand.Gmuwiki.global.annotation.RollbackService;
import mpersand.Gmuwiki.global.util.UserUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RollbackService
public class CreateBoardService {

    @Value("${cloud.aws.s3.url}")
    private String AWS_S3_ADDRESS;

    private final UserUtil userUtil;

    private final BoardRepository boardRepository;

    private final BoardFileService boardFileService;

    private final BoardFileRepository boardFileRepository;

    public void execute(CreateBoardRequest createBoardRequest, List<MultipartFile> multipartFileList) {

        User user = userUtil.currentUser();

        if(boardRepository.existsByTitle(createBoardRequest.getTitle())) {
            throw new ExistTitleException();
        }

        Board board = Board.builder()
                .title(createBoardRequest.getTitle())
                .content(createBoardRequest.getContent())
                .name(user.getName())
                .boardType(BoardType.from(createBoardRequest.getBoardType()))
                .user(user)
                .createdDate(LocalDateTime.now())
                .editedDate(LocalDateTime.now())
                .build();

        boardRepository.save(board);

        if (multipartFileList != null) {
            List<String> uploadFile = boardFileService.uploadFile(multipartFileList);
            for (String uploadFileUrl : uploadFile) {
                boardFileRepository.save(saveToUrl(board, uploadFileUrl));
            }
        }

    }

        private BoardFile saveToUrl(Board board, String uploadFileUrl) {

            BoardFile boardFile = BoardFile.builder()
                    .board(board)
                    .url(AWS_S3_ADDRESS + uploadFileUrl)
                    .build();

            return boardFile;
        }
}
