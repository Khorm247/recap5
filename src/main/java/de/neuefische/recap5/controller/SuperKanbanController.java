package de.neuefische.recap5.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.recap5.model.Task.TaskObject;
import de.neuefische.recap5.service.SuperKanbanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class SuperKanbanController {
    private final SuperKanbanService service;


    @GetMapping
    public List<TaskObject> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskObject getTaskById(@PathVariable String id){
        return service.getTaskToEdit(id);
    }

    @PostMapping
    public TaskObject addNewTodoTask(@RequestBody String newTask) throws JsonProcessingException {
        return service.addNewTask(newTask);
    }

    @PutMapping("/{id}")
    public TaskObject updateTask(
            @RequestBody TaskObject taskObject,
            @PathVariable String id) {
        return service.updateTask(id, taskObject);
    }

    @DeleteMapping("/{id}")
    public List<TaskObject> deleteTodoItem(@PathVariable String id) {
        return service.deleteTask(id);
    }
}
