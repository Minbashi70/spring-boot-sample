package com.minbashi.project.service.impl;

import com.minbashi.project.exception.ApplicationBusinessException;
import com.minbashi.project.exception.ExceptionMessage;
import com.minbashi.project.mapper.FileDetailMapper;
import com.minbashi.project.model.FileDetail;
import com.minbashi.project.repositoy.FileDetailRepository;
import com.minbashi.project.service.FileDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class FileDetailServiceImpl implements FileDetailService {
    private final FileDetailRepository fileDetailRepository;
    private final FileDetailMapper fileDetailMapper;

    @Override
    public List<FileDetail> getAll() {
        return fileDetailRepository.findAll();
    }

    @Override
    public FileDetail getByCode(String code) {
        return fileDetailRepository.findByCode(code).orElseThrow(() -> new ApplicationBusinessException(ExceptionMessage.FILE_DETAIL_NOT_FOUND));
    }

    @Override
    public FileDetail save(FileDetail fileDetail) {
        return fileDetailRepository.save(fileDetail);
    }

    @Override
    public void deleteAll() {
        fileDetailRepository.deleteAll();
    }

    @Override
    public void uploadFile(MultipartFile multiPartFile) {
        List<FileDetail> fileDetails = fileDetailMapper.fileToEntities(multiPartFile);
        log.info("Going to save objects {}", fileDetails);
        try {
            fileDetailRepository.saveAll(fileDetails);
        } catch (DataIntegrityViolationException e) {
            throw new ApplicationBusinessException(ExceptionMessage.FILE_DETAIL_CODE_UNIQUE);
        }
    }

}
