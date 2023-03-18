package mpersand.Gmuwiki.domain.notice.service.impl;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.presentation.dto.response.NoticeIdResponse;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import mpersand.Gmuwiki.domain.notice.service.OneNoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OneNoticeServiceImpl implements OneNoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public NoticeIdResponse execute(Long id){
        Optional<Notice> notice = noticeRepository.findById(id);
        return new NoticeIdResponse
                (notice.get().getId(), notice.get().getName(),notice.get().getTitle(),notice.get().getContent());
    }

}
