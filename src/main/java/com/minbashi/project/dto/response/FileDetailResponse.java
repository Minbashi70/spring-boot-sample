package com.minbashi.project.dto.response;

import java.time.LocalDate;

public record FileDetailResponse(
        String source,
        String codeListCode,
        String code,
        String displayValue,
        String longDescription,
        LocalDate fromDate,
        LocalDate toDate,
        Integer sortingPriority) {
}