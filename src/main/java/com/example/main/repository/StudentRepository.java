package com.example.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.main.model.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer>{

	// Custom Method Declare For Query DSL
	List<StudentModel> findByName(String name);

	// Custom Method Declare For Query Annotation
	@Query("from StudentModel where depart = ?1")
	List<StudentModel> findDepart(String depart);

}
