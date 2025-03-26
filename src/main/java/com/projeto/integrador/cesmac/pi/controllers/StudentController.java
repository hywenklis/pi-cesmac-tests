package com.projeto.integrador.cesmac.pi.controllers;

import com.projeto.integrador.cesmac.pi.controllers.requests.StudentRequest;
import com.projeto.integrador.cesmac.pi.entities.StudentEntity;
import com.projeto.integrador.cesmac.pi.services.StudentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentEntity> list() {
        return studentService.listStudents();
    }

    @PostMapping
    public StudentEntity create(@Validated @RequestBody StudentRequest student) {
        return studentService.save(student);
    }
}
