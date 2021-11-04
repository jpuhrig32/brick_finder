package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.datasource.dto.PartImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<PartImpl, String> {
}
