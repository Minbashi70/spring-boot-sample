package com.minbashi.project.controller;

import com.minbashi.project.dto.response.FileDetailResponse;
import com.minbashi.project.mapper.FileDetailMapper;
import com.minbashi.project.service.FileDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/file-details")
public class FileDetailController {

    private final FileDetailService fileDetailService;
    private final FileDetailMapper fileDetailMapper;

    @GetMapping
    public ResponseEntity<List<FileDetailResponse>> getAll() {
        List<FileDetailResponse> response = fileDetailMapper.toResponse(fileDetailService.getAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<FileDetailResponse> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(fileDetailMapper.toResponse(fileDetailService.getByCode(code)));
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        fileDetailService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/upload-file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> uploadFile(@RequestPart MultipartFile multiPartFile) {
        fileDetailService.uploadFile(multiPartFile);
        return ResponseEntity.noContent().build();
    }
}
