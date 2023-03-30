package mpersand.Gmuwiki.domain.notice.service;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeIdResponse;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OneNoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional(rollbackFor = Exception.class)
    public NoticeIdResponse execute(Long id){
        Optional<Notice> notice = noticeRepository.findById(id);
        return new NoticeIdResponse
                (notice.get().getId(), notice.get().getName(),notice.get().getTitle(),notice.get().getContent());
    }
}
