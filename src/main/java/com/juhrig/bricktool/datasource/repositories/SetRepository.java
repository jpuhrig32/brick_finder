package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<Set, String> {
}
