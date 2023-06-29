package mpersand.Gmuwiki.global.annotation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Service
@Transactional(rollbackFor = Exception.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RollbackService {
}
