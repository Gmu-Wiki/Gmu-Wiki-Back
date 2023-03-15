package mpersand.Gmuwiki.domain.notice.service.impl;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.exception.NoticeNotFoundException;
import mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust.ModifyNoticeRequest;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import mpersand.Gmuwiki.domain.notice.service.ModifyNoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModifyNoticeServiceImpl implements ModifyNoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void excute(Long id, ModifyNoticeRequest modifyNoticeRequest){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(()-> new NoticeNotFoundException("게시글을 찾을 수 없습니다"));
        notice.update(modifyNoticeRequest);
    }
}

