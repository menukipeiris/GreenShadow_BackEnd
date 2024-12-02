package lk.ijse.aad.greenShadow.dao;

import lk.ijse.aad.greenShadow.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffDAO extends JpaRepository<StaffEntity,String> {
    @Query("SELECT s FROM StaffEntity s WHERE s.firstName IN :staffs")
    List<StaffEntity> findByStaffNameList(@Param("staffs") List<String> staffs);

    @Query("SELECT s FROM StaffEntity s WHERE s.firstName = :first_name")
    Optional<StaffEntity> findByStaffName(@Param("first_name") String assignedStaff);
}
