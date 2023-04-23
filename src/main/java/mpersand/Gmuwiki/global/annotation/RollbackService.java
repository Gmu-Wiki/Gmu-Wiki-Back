package mpersand.Gmuwiki.global.annotation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public @interface RollbackService {
}
