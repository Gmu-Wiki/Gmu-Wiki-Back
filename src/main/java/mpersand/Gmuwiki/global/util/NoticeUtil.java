package mpersand.Gmuwiki.global.util;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.exception.NoticeNotFoundException;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeUtil {

    private final NoticeRepository noticeRepository;

    public Notice findNoticeById(Long id) {

        return noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException());
    }
}