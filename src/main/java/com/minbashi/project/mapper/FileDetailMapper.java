package com.minbashi.project.mapper;

import com.minbashi.project.dto.response.FileDetailResponse;
import com.minbashi.project.model.FileDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileDetailMapper {
    FileDetailResponse toResponse(FileDetail fileDetail);

    List<FileDetailResponse> toResponse(List<FileDetail> fileDetails);
}
