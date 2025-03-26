package com.projeto.integrador.cesmac.pi.integrations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.projeto.integrador.cesmac.pi.controllers.requests.StudentRequest;
import com.projeto.integrador.cesmac.pi.entities.StudentEntity;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class StudentControllerIntegrationTest extends IntegrationTestAbstract {

    @Test
    @DisplayName("should return OK status when students exist")
    void list_shouldReturnOkStatus_whenStudentsExist() throws Exception {
        final var student1 = StudentEntity.builder()
            .name("John")
            .registrationNumber("123456")
            .build();

        final var student2 = StudentEntity.builder()
            .name("Mary")
            .registrationNumber("654321")
            .build();

        studentRepository.saveAll(List.of(student1, student2));

        mockMvc.perform(get("/students")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("John"))
            .andExpect(jsonPath("$[1].name").value("Mary"));
    }

    @Test
    @DisplayName("should return empty list when no students exist")
    void list_shouldReturnEmptyList_whenNoStudentsExist() throws Exception {
        mockMvc.perform(get("/students")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @DisplayName("should return created student when data is valid")
    void create_shouldReturnCreatedStudent_whenDataIsValid() throws Exception {
        final var student = StudentRequest.builder()
            .name("Peter")
            .registrationNumber("111111")
            .build();

        String json = objectMapper.writeValueAsString(student);

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Peter"))
            .andExpect(jsonPath("$.registrationNumber").value("111111"));
    }

    @Test
    @DisplayName("should return bad request when request is invalid")
    void create_shouldReturnBadRequest_whenRequestIsInvalid() throws Exception {
        final var studentInvalidRequest = StudentRequest.builder()
            .name("")
            .registrationNumber("")
            .build();

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentInvalidRequest)))
            .andExpect(status().isBadRequest());
    }
}
