package com.minbashi.project.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse(
        String message,
        String code,
        HttpStatus httpStatus,
        String path,
        LocalDateTime timestamp) {
}
