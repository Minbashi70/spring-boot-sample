package com.minbashi.project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationBusinessException extends RuntimeException {

    private ExceptionMessage exceptionMessage;

    public ApplicationBusinessException(String message) {
        super(message);
    }

    public ApplicationBusinessException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.toString());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
