package com.projeto.integrador.cesmac.pi.repositories;

import com.projeto.integrador.cesmac.pi.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
