package com.dottcom.regestration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dottcom.regestration.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
