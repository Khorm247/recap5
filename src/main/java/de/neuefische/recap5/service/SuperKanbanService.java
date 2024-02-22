package de.neuefische.recap5.service;

import de.neuefische.recap5.model.Task.DTOs.TaskDto;
import de.neuefische.recap5.model.Task.TaskObject;
import de.neuefische.recap5.repo.SuperKanbanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuperKanbanService {

    //private final RestClient client;
    private final SuperKanbanRepo repo;
    private final IdService idService;
    //List<TaskObject> repo;

    public TaskObject addNewTask(TaskDto newTask){
        TaskObject task = new TaskObject(idService.generateUUID(), newTask.description(), newTask.status());
        return repo.save(task);
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
