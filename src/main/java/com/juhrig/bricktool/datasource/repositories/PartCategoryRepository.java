package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.datasource.dto.PartCategoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartCategoryRepository extends JpaRepository<PartCategoryImpl, Integer> {
}
