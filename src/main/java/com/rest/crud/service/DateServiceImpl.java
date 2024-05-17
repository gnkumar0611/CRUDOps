package com.rest.crud.service;

import com.rest.crud.model.DateTask;
import com.rest.crud.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DateServiceImpl implements DateService{
    private final DateRepository dateRepository;

    @Autowired
    public DateServiceImpl(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    @Override
    public List<DateTask> getAllTasks() {
        return dateRepository.findAll();
    }

    @Override
    public Optional<DateTask> getTaskById(Long id) {
        return dateRepository.findById(id);
    }

    @Override
    public DateTask saveTask(DateTask task) {
        return dateRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        dateRepository.deleteById(id);
    }
}
