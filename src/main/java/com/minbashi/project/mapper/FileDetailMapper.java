package com.minbashi.project.mapper;

import com.minbashi.project.dto.response.FileDetailResponse;
import com.minbashi.project.model.FileDetail;
import com.opencsv.CSVReader;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FileDetailMapper {
    public abstract FileDetailResponse toResponse(FileDetail fileDetail);

    public abstract List<FileDetailResponse> toResponse(List<FileDetail> fileDetails);

    public List<FileDetail> fileToEntities(MultipartFile multipartFile) {
        List<FileDetail> fileDetails = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(multipartFile.getInputStream()))) {
            String[] values;
            csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                FileDetail code = new FileDetail();
                code.setSource(values[0].replace("\"", ""));
                code.setCodeListCode(values[1].replace("\"", ""));
                code.setCode(values[2].replace("\"", ""));
                code.setDisplayValue(values[3].replace("\"", ""));
                code.setLongDescription(values[4].replace("\"", ""));
                code.setFromDate(LocalDate.parse(values[5].replace("\"", ""), formatter));
                String toDate = values[6].replace("\"", "");
                code.setToDate(toDate.isBlank() ? null : LocalDate.parse(toDate, formatter));
                String peririty = values[7].replace("\"", "");
                code.setSortingPriority(peririty.isBlank() ? null : Integer.parseInt(peririty));

                fileDetails.add(code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileDetails;
    }
}
