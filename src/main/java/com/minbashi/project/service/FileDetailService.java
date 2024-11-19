package com.minbashi.project.service;

import com.minbashi.project.model.FileDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileDetailService {
    List<FileDetail> getAll();

    FileDetail getByFileDetail(String FileDetail);

    FileDetail save(FileDetail fileDetail);

    void deleteAll();

    void uploadFile(MultipartFile multiPartFile);
}