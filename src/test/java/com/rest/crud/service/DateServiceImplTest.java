package com.rest.crud.service;

import com.rest.crud.model.DateTask;
import com.rest.crud.repository.DateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DateServiceImplTest {


    @Mock
    private DateRepository dateRepository;

    @InjectMocks
    private DateServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTasks() {
        DateTask task1 = new DateTask();
        task1.setId(1L);
        task1.setDescription("Task 1");

        DateTask task2 = new DateTask();
        task2.setId(2L);
        task2.setDescription("Task 2");


        when(dateRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<DateTask> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
        verify(dateRepository, times(1)).findAll();
    }

    @Test
    void getTaskById() {
        DateTask task = new DateTask();
        task.setId(1L);
        task.setDescription("Task 1");

        when(dateRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<DateTask> retrievedTask = taskService.getTaskById(1L);

        assertTrue(retrievedTask.isPresent());
        assertEquals("Task 1", retrievedTask.get().getDescription());
        verify(dateRepository, times(1)).findById(1L);
    }

    @Test
    void saveTask() {
        DateTask task = new DateTask();
        task.setDescription("New Task");

        when(dateRepository.save(task)).thenReturn(task);

        DateTask savedTask = taskService.saveTask(task);

        assertNotNull(savedTask);
        assertEquals("New Task", savedTask.getDescription());
        verify(dateRepository, times(1)).save(task);
    }

    @Test
    void deleteTask() {
        Long taskId = 1L;

        doNothing().when(dateRepository).deleteById(taskId);

        taskService.deleteTask(taskId);

        verify(dateRepository, times(1)).deleteById(taskId);
    }

}