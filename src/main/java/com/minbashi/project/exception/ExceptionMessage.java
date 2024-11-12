package com.minbashi.project.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    INTERNAL_SERVER_ERROR("100001"),
    PERSON_ALREADY_EXISTS("100401"),
    PERSON_NOT_FOUND("10000404");

    private final String code;

    ExceptionMessage(String code) {
        this.code = code;
    }

}
