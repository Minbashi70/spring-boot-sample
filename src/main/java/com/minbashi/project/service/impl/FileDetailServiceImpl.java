package com.minbashi.project.service.impl;

import com.minbashi.project.exception.ApplicationBusinessException;
import com.minbashi.project.exception.ExceptionMessage;
import com.minbashi.project.model.FileDetail;
import com.minbashi.project.repositoy.FileDetailRepository;
import com.minbashi.project.service.FileDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class FileDetailServiceImpl implements FileDetailService {
    private final FileDetailRepository fileDetailRepository;
    private final JobLauncher jobLauncher;
    private final Job job;

    @Override
    public List<FileDetail> getAll() {
        return fileDetailRepository.findAll();
    }

    @Override
    public FileDetail getByFileDetail(String fileDetail) {
        return fileDetailRepository.findByCode(fileDetail).orElseThrow(() -> new ApplicationBusinessException(ExceptionMessage.FILE_DETAIL_NOT_FOUND));
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
        try {
            File file = multipartFileToFile(multiPartFile);
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("startAt", System.currentTimeMillis())
                    .addString("fileName", file.getAbsolutePath())
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);

        } catch (JobExecutionAlreadyRunningException | JobParametersInvalidException
                 | JobInstanceAlreadyCompleteException | JobRestartException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private File multipartFileToFile(MultipartFile multipartFile) {
        try {
            String localTempFilePath = makeLocalTempFilePath(multipartFile.getOriginalFilename());
            File file = new File(localTempFilePath);
            multipartFile.transferTo(file);
            return file;
        } catch (IOException exception) {
            log.error("Exception has been thrown during converting multipart file to file");
            return null;
        }
    }

    private String makeLocalTempFilePath(String fileName) throws IOException {
        String localTempDir = Files.createTempDirectory("temp").toString();
        String localFilePath = localTempDir.concat("/").concat(fileName);
        return localFilePath;
    }

}
