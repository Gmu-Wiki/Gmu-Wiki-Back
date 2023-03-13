package mpersand.Gmuwiki.domain.notice.service;

import mpersand.Gmuwiki.domain.notice.presentation.dto.reqeust.ModifyNoticeRequest;

public interface ModifyNoticeService {
    public void modify(Long id, ModifyNoticeRequest modifyNoticeRequest);
}
