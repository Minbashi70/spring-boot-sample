package com.minbashi.project.batch;

import com.minbashi.project.model.FileDetail;
import com.minbashi.project.repositoy.FileDetailRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FileDetailItemWriter implements ItemWriter<FileDetail> {

    @Autowired
    private FileDetailRepository fileDetailRepository;

    @Override
    public void write(Chunk<? extends FileDetail> chunk) throws Exception {
        fileDetailRepository.saveAll(chunk);

    }
}