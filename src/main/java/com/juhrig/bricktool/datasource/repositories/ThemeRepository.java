package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.datasource.dto.ThemeImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeImpl, Integer> {
}
