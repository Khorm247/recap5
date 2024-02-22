package de.neuefische.recap5.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SuperKanbanControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    void getAllTasks() throws Exception {
        // GIVEN
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
        // THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    []
                """));

    }

    @Test
    void addNewTodoTask() throws Exception {
        // GIVEN
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "description": "test-description",
                            "status": "OPEN"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                        "description": "test-description",
                                        "status": "OPEN"
                            }
                            """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }
}