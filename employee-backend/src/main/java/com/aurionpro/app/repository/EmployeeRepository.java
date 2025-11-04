package com.aurionpro.app.repository;

import com.aurionpro.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByIdAndIsDeletedFalse(Long id);
    @Query("select e from Employee e JOIN FETCH e.role r JOIN FETCH r.department WHERE e.isDeleted = false")
    List<Employee> findAllByIsDeletedFalse();
    boolean existsByEmailAndIsDeletedFalse(String email);

    boolean existsByIdAndIsDeletedFalse(Long id);
    List<Employee> findAllByIsDeletedTrue();
    
    boolean existsByPhoneNumberAndIsDeletedFalse(String phoneNumber);
    Optional<Employee> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);
    
    Optional<Employee> findByEmailAndIsDeletedFalse(String email);

}