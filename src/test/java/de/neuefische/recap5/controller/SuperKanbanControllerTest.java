package de.neuefische.recap5.controller;

import de.neuefische.recap5.model.Task.TaskObject;
import de.neuefische.recap5.model.Task.TaskStatus;
import de.neuefische.recap5.repo.SuperKanbanRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SuperKanbanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SuperKanbanRepo todoRepository;
    @Test
    void getAllTasks() throws Exception {
        // GIVEN
        // WHEN
        mockMvc.perform(get("/api/todo"))
        // THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    []
                """));

    }

    @Test
    @DirtiesContext
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


    @Test
    @DirtiesContext
    void updateTask() throws Exception {
        //GIVEN
        TaskObject existingTodo = new TaskObject("1", "test-description", TaskStatus.OPEN);
        todoRepository.save(existingTodo);

        //WHEN
        mockMvc.perform(put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "description": "test-description-2",
                                        "status": "IN_PROGRESS"
                                    }
                                """))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "id": "1",
                                "description": "test-description-2",
                                "status": "IN_PROGRESS"
                            }
                        """));
    }

    @Test
    @DirtiesContext
    void getTaskById() throws Exception {
        //GIVEN
        TaskObject existingTodo = new TaskObject("1", "test-description", TaskStatus.OPEN);
        todoRepository.save(existingTodo);

        //WHEN
        mockMvc.perform(get("/api/todo/1"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "id": "1",
                                "description": "test-description",
                                "status": "OPEN"
                            }
                        """));

    }

    @Test
    @DirtiesContext
    void deleteTodoById() throws Exception {
        //GIVEN
        TaskObject existingTodo = new TaskObject("1", "test-description", TaskStatus.OPEN);
        todoRepository.save(existingTodo);

        //WHEN
        mockMvc.perform(delete("/api/todo/1"))
                //THEN
                .andExpect(status().isOk());
    }
}