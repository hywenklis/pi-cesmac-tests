package com.projeto.integrador.cesmac.pi.units;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.projeto.integrador.cesmac.pi.controllers.requests.StudentRequest;
import com.projeto.integrador.cesmac.pi.entities.StudentEntity;
import com.projeto.integrador.cesmac.pi.repositories.StudentRepository;
import com.projeto.integrador.cesmac.pi.services.StudentService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class StudentServiceTest extends UnitTestAbstract {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    @DisplayName("should return student list when students exist")
    void listStudents_shouldReturnStudentList_whenStudentsExist() {
        final var student1 = StudentEntity.builder()
            .name("John")
            .registrationNumber("123456")
            .build();

        final var student2 = StudentEntity.builder()
            .name("Mary")
            .registrationNumber("654321")
            .build();

        List<StudentEntity> students = List.of(student1, student2);

        when(studentRepository.findAll()).thenReturn(students);

        List<StudentEntity> result = studentService.listStudents();

        assertThat(result).usingRecursiveAssertion().isEqualTo(students);

        assertThat(result)
            .hasSize(2)
            .extracting(StudentEntity::getName)
            .containsExactly("John", "Mary");
    }

    @Test
    @DisplayName("should return empty list when no students exist")
    void listStudents_shouldReturnEmptyList_whenNoStudentsExist() {
        when(studentRepository.findAll()).thenReturn(Collections.emptyList());

        List<StudentEntity> result = studentService.listStudents();

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("should return saved student when data is valid")
    void saveStudent_shouldReturnSavedStudent_whenDataIsValid() {
        final var request = StudentRequest.builder()
            .name("Peter")
            .registrationNumber("12345")
            .build();

        final var student = StudentEntity.builder()
            .name(request.name())
            .registrationNumber(request.registrationNumber())
            .build();

        when(studentRepository.save(student)).thenReturn(student);

        StudentEntity result = studentService.save(request);

        assertThat(result)
            .isNotNull()
            .hasFieldOrPropertyWithValue("name", "Peter")
            .hasFieldOrPropertyWithValue("registrationNumber", "12345");
    }

    @Test
    @DisplayName("should throw exception when repository fails")
    void saveStudent_shouldThrowException_whenRepositoryFails() {
        final var request = StudentRequest.builder()
            .name("Anna")
            .registrationNumber("12345")
            .build();

        final var student = StudentEntity.builder()
            .name(request.name())
            .registrationNumber(request.registrationNumber())
            .build();
        when(studentRepository.save(student)).thenThrow(new RuntimeException("Database error"));

        assertThatThrownBy(() -> studentService.save(request))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("Database error");
    }

    @Test
    @DisplayName("should call save exactly once")
    void saveStudent_shouldCallSaveExactlyOnce() {
        final var request = StudentRequest.builder()
            .name("Charles")
            .registrationNumber("12345")
            .build();

        final var student = StudentEntity.builder()
            .name(request.name())
            .registrationNumber(request.registrationNumber())
            .build();

        when(studentRepository.save(student)).thenReturn(student);

        studentService.save(request);

        verify(studentRepository, times(1)).save(student);
    }

    @Test
    @DisplayName("should return null safe list when repository returns null")
    void listStudents_shouldReturnNullSafeList_whenRepositoryReturnsNull() {
        when(studentRepository.findAll()).thenReturn(null);

        List<StudentEntity> result = studentService.listStudents();

        assertThat(result).isNull();
    }
}
