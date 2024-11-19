package com.minbashi.project.service.impl;

import com.minbashi.project.model.FileDetail;
import com.minbashi.project.service.FileDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InvalidClientItemProcessor implements ItemProcessor<FileDetail, FileDetail> {
    private final FileDetailService fileDetailService;

    @Override
    public FileDetail process(final FileDetail fileDetail) {
        return fileDetail;
    }
}