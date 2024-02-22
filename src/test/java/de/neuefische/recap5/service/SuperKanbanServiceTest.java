package de.neuefische.recap5.service;

import de.neuefische.recap5.model.Task.TaskObject;
import de.neuefische.recap5.repo.SuperKanbanRepo;
import org.junit.jupiter.api.Test;

import java.util.List;

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
}