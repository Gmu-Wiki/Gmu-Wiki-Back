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

    @Transactional(readOnly = true)
    public NoticeIdResponse execute(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        NoticeIdResponse noticeIdResponse = NoticeIdResponse.builder()
                .id(notice.getId())
                .content(notice.getContent())
                .title(notice.getTitle())
                .build();

        return noticeIdResponse;
    }
}
