package com.rest.crud.repository;

import com.rest.crud.model.DateTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateRepository extends JpaRepository<DateTask, Long> {
}
