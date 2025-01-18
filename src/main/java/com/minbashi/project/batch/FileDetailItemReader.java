package com.minbashi.project.batch;

import com.minbashi.project.model.FileDetail;
import com.opencsv.CSVReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileDetailItemReader implements ItemReader<FileDetail> {

    private List<FileDetail> fileDetails;
    private int currentIndex = 0;

    public FileDetailItemReader() {
        this.fileDetails = readData();
    }

    @Override
    public FileDetail read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (currentIndex < fileDetails.size()) {
            return fileDetails.get(currentIndex++);
        } else {
            return null;
        }
    }

    private List<FileDetail> readData() {
        List<FileDetail> fileDetails = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String filePath = "exercise.csv";

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values;
            csvReader.readNext();

            while ((values = csvReader.readNext()) != null) {
                FileDetail fileDetail = new FileDetail();
                fileDetail.setSource(values[0].replace("\"", ""));
                fileDetail.setCodeListCode(values[1].replace("\"", ""));
                fileDetail.setCode(values[2].replace("\"", ""));
                fileDetail.setDisplayValue(values[3].replace("\"", ""));
                fileDetail.setLongDescription(values[4].replace("\"", ""));
                fileDetail.setFromDate(LocalDate.parse(values[5].replace("\"", ""), formatter));
                String toDate = values[6].replace("\"", "");
                fileDetail.setToDate(toDate.isBlank() ? null : LocalDate.parse(toDate, formatter));
                String priority = values[7].replace("\"", "");
                fileDetail.setSortingPriority(priority.isBlank() ? null : Integer.parseInt(priority));

                fileDetails.add(fileDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileDetails;
    }
}