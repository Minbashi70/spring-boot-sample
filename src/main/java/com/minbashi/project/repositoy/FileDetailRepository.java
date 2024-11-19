package com.minbashi.project.repositoy;

import com.minbashi.project.model.FileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileDetailRepository extends JpaRepository<FileDetail, Long> {
    Optional<FileDetail> findByCode(String code);
}
