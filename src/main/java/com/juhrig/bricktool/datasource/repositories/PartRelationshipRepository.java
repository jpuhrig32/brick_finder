package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.datasource.dto.PartRelationshipImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartRelationshipRepository extends JpaRepository<PartRelationshipImpl, Integer> {

    @Query(value="SELECT t from PartRelationship t where t.childPartNumber = 1?", nativeQuery = true)
    List<PartRelationshipImpl> findByChildPartNumber(String childPartNumber);

    @Query(value ="SELECT t from PartRelationship t where t.childPartNumber in :childPartNumbers", nativeQuery = true)
    List<PartRelationshipImpl> findAllByChildPartNumber(@Param("childPartNumbers") List<String> childPartNumbers);

    @Query(value="SELECT t from PartRelationship t where t.parentPartNumber = 1?", nativeQuery = true)
    List<PartRelationshipImpl> findByParentPartNumber(String parentPartNumber);

    @Query(value ="SELECT t from PartRelationship t where t.parentPartNumber in :parentPartNumbers", nativeQuery = true)
    List<PartRelationshipImpl> findAllByParentPartNumber(@Param("parentPartNumbers") List<String> parentPartNumbers);

}
