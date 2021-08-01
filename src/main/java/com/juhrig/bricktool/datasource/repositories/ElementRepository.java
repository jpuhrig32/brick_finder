package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<Element, Integer> {
}
