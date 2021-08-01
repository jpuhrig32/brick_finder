package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, String> {
}
