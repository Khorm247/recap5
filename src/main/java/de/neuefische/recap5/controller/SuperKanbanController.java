package de.neuefische.recap5.controller;

import de.neuefische.recap5.model.Task.DTOs.TaskDto;
import de.neuefische.recap5.model.Task.TaskObject;
import de.neuefische.recap5.service.SuperKanbanService;
import lombok.RequiredArgsConstructor;
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
    public TaskObject addNewTodoTask(@RequestBody TaskDto taskDto) {
        return service.addNewTask(taskDto);
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
