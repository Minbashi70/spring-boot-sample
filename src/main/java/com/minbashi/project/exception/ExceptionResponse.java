package com.minbashi.project.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse(
        String message,
        String FileDetail,
        HttpStatus httpStatus,
        String path,
        LocalDateTime timestamp) {
}
