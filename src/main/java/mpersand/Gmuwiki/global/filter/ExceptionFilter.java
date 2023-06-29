package mpersand.Gmuwiki.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import mpersand.Gmuwiki.global.error.ErrorCode;
import mpersand.Gmuwiki.global.error.ErrorMessage;
import mpersand.Gmuwiki.global.error.GmuwikiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static mpersand.Gmuwiki.global.error.ErrorCode.*;


@Component
@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    private final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

    public void setErrorResponse(ErrorCode errorCode, HttpServletResponse response) throws IOException {
        response.setStatus(errorCode.getStatus());
        response.setContentType("application/json; charset=utf-8");

        ErrorMessage errorMessage = new ErrorMessage(errorCode);

        String errorResponseEntityToJson = objectMapper.writeValueAsString(errorMessage);
        response.getWriter().write(errorResponseEntityToJson);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException ex) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 ExpiredJwtException 발생 ===================");
            setErrorResponse(TOKEN_IS_EXPIRED, response);
        } catch (JwtException | GmuwikiException ex) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 TokenNotValidException 발생 ===================");
            setErrorResponse(TOKEN_NOT_VALID, response);
        } catch (Exception ex) {
            log.debug("================= [ ExceptionHandlerFilter ] 에서 Exception 발생 ===================");
            setErrorResponse(UNKNOWN_ERROR, response);
        }
    }
}

