package dev.be.homework.config;

import dev.be.homework.solution.dto.CommonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalHandler {
    @ExceptionHandler(Exception.class)
    public CommonResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new CommonResponse(e.getMessage(), e.getCause());
    }
}
