package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.PartCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartCategoryRepository extends JpaRepository<PartCategory, Integer> {
}
