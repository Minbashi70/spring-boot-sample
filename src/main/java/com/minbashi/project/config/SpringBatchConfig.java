package com.minbashi.project.config;

import com.minbashi.project.model.FileDetail;
import com.minbashi.project.repositoy.FileDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {
    private FileDetailRepository repository;

//    @Value("#{jobParameters[fileName]}")
//    private String fileName;

    public class CustomLineMapper implements LineMapper<FileDetail> {
        @Override
        public FileDetail mapLine(String line, int lineNumber) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String[] record = line.split(",");
            FileDetail domainObject = new FileDetail();
            domainObject.setSource(record.length > 0 && !record[0].isEmpty() ? record[0] : null);
            domainObject.setCodeListCode(record.length > 1 && !record[1].isEmpty() ? record[1] : null);
            domainObject.setCode(record.length > 2 && !record[2].isEmpty() ? record[2] : null);
            domainObject.setDisplayValue(record.length > 3 && !record[3].isEmpty() ? record[3] : null);
            domainObject.setLongDescription(record.length > 4 && !record[4].isEmpty() ? record[4] : null);
            domainObject.setFromDate(record.length > 5 && !record[5].isEmpty() ? LocalDate.parse(record[5], formatter) : null);
            domainObject.setToDate(record.length > 6 && !record[6].isEmpty() ? LocalDate.parse(record[6], formatter) : null);
            domainObject.setSortingPriority(record.length > 7 && !record[7].isEmpty() ? Integer.parseInt(record[7]) : null);
            return domainObject;
        }
    }

    @Bean
    public LineMapper<FileDetail> lineMapper() {
        return new CustomLineMapper();
    }

    @Bean
    @JobScope
    public FlatFileItemReader<FileDetail> reader(@Value("#{jobParameters[fileName]}") String fileName) {
        FlatFileItemReader<FileDetail> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource(fileName)); // Use the passed filename
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }


    @Bean
    public FileDetailProcessor processor() {
        return new FileDetailProcessor();
    }

    @Bean
    public RepositoryItemWriter<FileDetail> writer() {
        RepositoryItemWriter<FileDetail> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, RepositoryItemWriter<FileDetail> writer) {
        return new StepBuilder("step1", jobRepository)
                .<FileDetail, FileDetail>chunk(10, transactionManager)
                .reader(reader(null))
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job importCustomerJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importCustomer", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .build();
    }
}
