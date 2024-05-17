package com.rest.crud.controller;

import com.rest.crud.model.DateTask;
import com.rest.crud.service.DateService;
import com.rest.crud.utilities.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dates")
public class DateController {


    private final DateService dateService;

    @Autowired
    public DateController(DateService taskService) {
        this.dateService = taskService;
    }

    @GetMapping
    public List<DateTask> getAllTasks() {
        return dateService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DateTask> getTaskById(@PathVariable Long id) {
        Optional<DateTask> task = dateService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DateTask createTask(@RequestBody DateTask task) {
        return dateService.saveTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DateTask> updateTask(@PathVariable Long id, @RequestBody DateTask task) {
        Optional<DateTask> existingTask = dateService.getTaskById(id);
        if (existingTask.isPresent()) {
            task.setId(id);
            return ResponseEntity.ok(dateService.saveTask(task));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        dateService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/convertDate")
    public LocalDateTime convertDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String date) {
        return DateUtil.convertToDateTime(date);
    }

}
