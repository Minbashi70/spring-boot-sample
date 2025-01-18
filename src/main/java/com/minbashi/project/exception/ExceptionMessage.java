package com.minbashi.project.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    INTERNAL_SERVER_ERROR("100001"),
    FILE_DETAIL_CODE_UNIQUE("10000400"),
    FILE_DETAIL_NOT_FOUND("10000404");

    private final String code;

    ExceptionMessage(String code) {
        this.code = code;
    }

}
