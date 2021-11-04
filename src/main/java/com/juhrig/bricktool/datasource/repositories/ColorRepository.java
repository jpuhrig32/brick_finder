package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.datasource.dto.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

}
