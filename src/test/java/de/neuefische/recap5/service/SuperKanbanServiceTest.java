package de.neuefische.recap5.service;

import de.neuefische.recap5.model.Task.DTOs.TaskDto;
import de.neuefische.recap5.model.Task.TaskObject;
import de.neuefische.recap5.model.Task.TaskStatus;
import de.neuefische.recap5.repo.SuperKanbanRepo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SuperKanbanServiceTest {

    private final SuperKanbanRepo mockRepository = mock(SuperKanbanRepo.class);
    private final IdService mockIdService = mock(IdService.class);

    SuperKanbanService service = new SuperKanbanService(mockRepository, mockIdService);

    @Test
    void getAllTasks_whenCalled_thenReturnCompleteTaskList() {
        // GIVEN
        TaskObject task1 = new TaskObject("1", "Task 1", "open");
        TaskObject task2 = new TaskObject("2", "Task 2", "open");
        TaskObject task3 = new TaskObject("3", "Task 3", "open");
        List<TaskObject> expected = List.of(task1, task2, task3);
        when(mockRepository.findAll()).thenReturn(expected);
        // WHEN
        List<TaskObject> actual = service.getAllTasks();
        // THEN

        verify(mockRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void addNewTask(){
        // GIVEN
        String testID = "123456";
        TaskDto taskDto = new TaskDto("Task 1", "open");
        TaskObject expected = new TaskObject(testID, "Task 1", "open");

        when(mockIdService.generateUUID()).thenReturn(testID);
        when(mockRepository.save(expected)).thenReturn(expected);
        // WHEN
        TaskObject actual = service.addNewTask(taskDto);
        // THEN
        verify(mockIdService).generateUUID();
        verify(mockRepository).save(expected);
        assertEquals(expected, actual);
    }

    @Test
    void updateTodo() {
        //GIVEN
        String id = "123";
        TaskDto todoToUpdate = new TaskDto("test-description", TaskStatus.IN_PROGRESS.toString());

        TaskObject updatedTodo = new TaskObject("123", "test-description", TaskStatus.IN_PROGRESS.toString());

        when(mockRepository.save(updatedTodo)).thenReturn(updatedTodo);

        //WHEN

        TaskObject actual = service.updateTask(id, todoToUpdate);

        //THEN
        verify(mockRepository).save(updatedTodo);

        assertEquals(updatedTodo, actual);
    }

    @Test
    void getTodoByIdTest_whenValidId_ThenReturnTodo() {

        //GIVEN
        String id = "1";
        TaskObject todo = new TaskObject("1", "test-description", TaskStatus.OPEN.toString());

        when(mockRepository.findById(id)).thenReturn(Optional.of(todo));

        //WHEN
        TaskObject actual = service.getTaskById(id);

        //THEN
        verify(mockRepository).findById(id);
        assertEquals(todo, actual);
    }

    @Test
    void getTodoByIdTest_whenInvalidId_ThenThrowException() {
        //GIVEN
        String id = "1";

        when(mockRepository.findById(id)).thenReturn(Optional.empty());

        //WHEN

        assertThrows(NoSuchElementException.class, () -> service.getTaskById(id));

        //THEN
        verify(mockRepository).findById(id);
    }

    @Test
    void deleteTodo() {
        //GIVEN
        String id = "1";
        doNothing().when(mockRepository).deleteById(id);

        //WHEN

        service.deleteTask(id);

        //THEN
        verify(mockRepository).deleteById(id);
    }
}