package com.projeto.integrador.cesmac.pi.services;

import com.projeto.integrador.cesmac.pi.controllers.requests.StudentRequest;
import com.projeto.integrador.cesmac.pi.entities.StudentEntity;
import com.projeto.integrador.cesmac.pi.repositories.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<StudentEntity> listStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    public StudentEntity save(StudentRequest student) {
        final var studentEntity = StudentEntity.builder()
            .name(student.name())
            .registrationNumber(student.registrationNumber())
            .build();

        return studentRepository.save(studentEntity);
    }
}
