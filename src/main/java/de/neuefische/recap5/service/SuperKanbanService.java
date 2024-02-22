package de.neuefische.recap5.service;

import de.neuefische.recap5.model.Task.TaskObject;
import de.neuefische.recap5.repo.SuperKanbanRepo;
import org.springframework.core.task.TaskDecorator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuperKanbanService {

    //private final RestClient client;
    //private final SuperKanbanRepo repo;
    private int idCounter = 0;
    List<TaskObject> repo;

    public SuperKanbanService(SuperKanbanRepo repo){
        this.repo = new ArrayList<>();
    }

    public void addNewTask(TaskObject newTask) {
        newTask.setId(String.valueOf(idCounter++));
        repo.add(newTask);
    }

    public List<TaskObject> getAllTasks() {
        return repo;
    }

    public TaskObject getTaskToEdit(String id) {

        return repo.stream()
                //.filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public TaskObject updateTask(String id, TaskObject taskObject) {
        TaskObject tObject = repo.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);

        if(tObject != null){
            tObject.setDescription(taskObject.getDescription());
            tObject.setStatus(taskObject.getStatus());
            return tObject;
        }
        return null;
    }

    public List<TaskObject> deleteTask(String id) {
        TaskObject taskToDelete = repo.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
        repo.remove(taskToDelete);
        return repo;
    }
}
