package mpersand.Gmuwiki.global.annotation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public @interface AnnotationReadOnlyService {
}
