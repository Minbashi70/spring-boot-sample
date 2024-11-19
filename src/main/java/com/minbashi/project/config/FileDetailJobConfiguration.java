//package com.minbashi.project.config;
//
//import com.minbashi.project.model.FileDetail;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemStreamReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.FileSystemResource;
//
//@Configuration
//@RequiredArgsConstructor
//public class FileDetailJobConfiguration {
//    private final InvalidClientItemProcessor invalidClientItemProcessor;
//
//    @Bean
//    @StepScope
//    public ItemStreamReader<FileDetail> itemReader(@Value("#{jobParameters[invalidClientFileName]}") String fileName) {
//        return new StaxEventItemReaderBuilder<FileDetail>()
//                .name("ClientInfoReader")
//                .resource(new FileSystemResource(fileName))
//                .addFragmentRootElements("PersonInfo")
//                .unmarshaller(clientMarshaller)
//                .build();
//    }
//
//    @Bean
//    public ItemWriter<FileDetail> itemWriter() {
//        return items -> {
//        };
//    }
//
//    @Bean
//    public Step xmlJobStep(ItemReader<FileDetail> reader, StepBuilderFactory stepBuilderFactory) {
//
//        return stepBuilderFactory.get("xmlJobStep")
//                .<InvalidClient, InvalidClient>chunk(1000)
//                .faultTolerant()
//                .skip(Exception.class)
//                .skipLimit(20000000)
//                .reader(reader)
//                .writer(itemWriter())
//                .processor(invalidClientItemProcessor)
//                .build();
//    }
//
//    @Bean
//    Job invalidClientProcessJob(Step xmlJobStep, JobBuilderFactory jobBuilderFactory) {
//        return jobBuilderFactory.get("invalidClientProcessJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(xmlJobStep)
//                .end()
//                .build();
//    }
//
//}
