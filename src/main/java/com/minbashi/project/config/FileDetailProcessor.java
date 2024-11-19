package com.minbashi.project.config;

import com.minbashi.project.model.FileDetail;
import org.springframework.batch.item.ItemProcessor;

public class FileDetailProcessor implements ItemProcessor<FileDetail, FileDetail> {
    @Override
    public FileDetail process(FileDetail fileDetail) {
        return fileDetail;
    }
}
