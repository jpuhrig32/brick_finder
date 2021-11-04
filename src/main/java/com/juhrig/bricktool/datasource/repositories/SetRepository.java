package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.datasource.dto.SetImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<SetImpl, String> {
}
