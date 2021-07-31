package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorInventory extends JpaRepository<Color, Integer> {
}
