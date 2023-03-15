package mpersand.Gmuwiki.domain.notice.service;

import mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust.CreateNoticeRequest;

public interface CreateNoticeService {
    void excute(CreateNoticeRequest createNoticeRequest);
}
