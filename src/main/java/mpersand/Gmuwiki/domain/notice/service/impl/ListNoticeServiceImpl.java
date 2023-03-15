package mpersand.Gmuwiki.domain.notice.service.impl;

import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.domain.notice.entity.Notice;
import mpersand.Gmuwiki.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListNoticeServiceImpl {

    private final NoticeRepository noticeRepository;

    public List<Notice> excute(){
        return noticeRepository.findAll();
    }
}
