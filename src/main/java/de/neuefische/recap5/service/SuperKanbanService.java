package de.neuefische.recap5.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.recap5.model.Task.TaskObject;
import de.neuefische.recap5.repo.SuperKanbanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskDecorator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperKanbanService {

    //private final RestClient client;
    private final SuperKanbanRepo repo;
    private final IdService idService;
    //List<TaskObject> repo;

    public TaskObject addNewTask(String newTask) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TaskObject task = mapper.readValue(newTask, TaskObject.class);
        task.setId(idService.generateUUID());
        repo.save(task);
        return task;
    }

    public List<TaskObject> getAllTasks() {
        return repo.findAll();
    }

    public TaskObject getTaskToEdit(String id) {

        return repo.findAll().stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public TaskObject updateTask(String id, TaskObject taskObject) {
        TaskObject tObject = repo.findAll().stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(tObject != null){
            tObject.setDescription(taskObject.getDescription());
            tObject.setStatus(taskObject.getStatus());
            repo.save(tObject);
            return tObject;
        }
        return null;
    }

    public List<TaskObject> deleteTask(String id) {
        TaskObject taskToDelete = repo.findAll().stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
        assert taskToDelete != null;
        repo.delete(taskToDelete);
        return repo.findAll();
    }
}
