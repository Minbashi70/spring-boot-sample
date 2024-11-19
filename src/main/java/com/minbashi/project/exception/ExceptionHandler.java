package com.minbashi.project.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandler {
    private final MessageSource messageSource;

    @org.springframework.web.bind.annotation.ExceptionHandler(ApplicationBusinessException.class)
    public @ResponseBody ResponseEntity<ExceptionResponse> handleDomainException(ApplicationBusinessException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(ExceptionResponse.builder()
                .FileDetail(e.getExceptionMessage().getFileDetail())
                .httpStatus(e.getStatus())
                .message(e.getExceptionMessage().name())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public @ResponseBody ResponseEntity<ExceptionResponse> handleException(Exception e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse.builder()
                .FileDetail(ExceptionMessage.INTERNAL_SERVER_ERROR.getFileDetail())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build());
    }
}
