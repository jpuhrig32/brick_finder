package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, String> {
}
