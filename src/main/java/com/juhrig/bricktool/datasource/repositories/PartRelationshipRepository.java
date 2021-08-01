package com.juhrig.bricktool.datasource.repositories;

import com.juhrig.bricktool.dto.Part;
import com.juhrig.bricktool.dto.PartRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartRelationshipRepository extends JpaRepository<PartRelationship, Integer> {

    @Query(value="SELECT t from PartRelationship t where t.childPartNumber = 1?")
    public List<PartRelationship> findByChildPartNumber(String childPartNumber);

    @Query(value ="SELECT t from PartRelationship t where t.childPartNumber in :childPartNumbers")
    public List<PartRelationship> findAllByChildPartNumber(@Param("childPartNumbers")List<String> childPartNumbers);

    @Query(value="SELECT t from PartRelationship t where t.parentPartNumber = 1?")
    public List<PartRelationship> findByParentPartNumber(String parentPartNumber);

    @Query(value ="SELECT t from PartRelationship t where t.parentPartNumber in :parentPartNumbers")
    public List<PartRelationship> findAllByParentPartNumber(@Param("parentPartNumbers")List<String> parentPartNumbers);

}
