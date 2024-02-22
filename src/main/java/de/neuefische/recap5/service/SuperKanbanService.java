package de.neuefische.recap5.service;

import de.neuefische.recap5.model.Task.DTOs.TaskDto;
import de.neuefische.recap5.model.Task.TaskObject;
import de.neuefische.recap5.repo.SuperKanbanRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SuperKanbanService {

    private final SuperKanbanRepo repo;
    private final IdService idService;

    public TaskObject addNewTask(TaskDto newTask){
        TaskObject task = new TaskObject(idService.generateUUID(), newTask.description(), newTask.status());
        return repo.save(task);
    }

    public List<TaskObject> getAllTasks() {
        return repo.findAll();
    }

    public TaskObject getTaskById(String id) {
        return repo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public TaskObject updateTask(String id, TaskDto taskDto) {
        TaskObject newTask = new TaskObject(id, taskDto.description(), taskDto.status());
        return repo.save(newTask);
    }

    public List<TaskObject> deleteTask(String id) {
        repo.deleteById(id);
        return repo.findAll();
    }
}
