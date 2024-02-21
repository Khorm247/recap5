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
        // was passiert hier?
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskObject getTaskById(@PathVariable String id){
        return service.getTaskToEdit(id);
    }

    @PostMapping
    public TaskObject addNewTodoTask(@RequestBody String newTask) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TaskObject task = mapper.readValue(newTask, TaskObject.class);
        service.addNewTask(task);

        return task;
    }

    @PutMapping("/{id}")
    public TaskObject updateTask(
            @RequestBody TaskObject taskObject
    ) {
        taskObject.setStatus("IN_PROGRESS");
        return taskObject;
    }

    @DeleteMapping("/delete")
    public String deleteTask(@RequestBody TaskObject taskObject) {
        service.deleteTask(taskObject);
        return "taskObject";
    }

    @DeleteMapping("/{id}")
    public void deleteTodoItem(@PathVariable String id) {
        //service.deleteTaskById(id);
    }
}
