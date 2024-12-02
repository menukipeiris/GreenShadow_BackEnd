package lk.ijse.aad.greenShadow.dao;

import lk.ijse.aad.greenShadow.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldDAO extends JpaRepository<FieldEntity,String> {
    @Query("SELECT f FROM FieldEntity f WHERE f.fieldName = :field_name")
    Optional<FieldEntity> findByFieldName(@Param("field_name") String field_name);

    @Query("SELECT f FROM FieldEntity f WHERE f.fieldName IN :field_name")
    List<FieldEntity> findByFieldNameList(@Param("field_name") List<String> field_name);
}
