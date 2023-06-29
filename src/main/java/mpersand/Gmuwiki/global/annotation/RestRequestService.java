package mpersand.Gmuwiki.global.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RestController
@RequestMapping
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestRequestService {
    
    @AliasFor(annotation = RequestMapping.class)
    String value() default "";
}