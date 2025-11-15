package org.gandalftheblack.mytodolist.api.application.service;

import lombok.RequiredArgsConstructor;
import org.gandalftheblack.mytodolist.api.application.mapper.TaskListMapper;
import org.gandalftheblack.mytodolist.api.domain.model.TaskList;
import org.gandalftheblack.mytodolist.api.infrastructure.repository.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTaskListUseCase {
    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;

    public TaskList execute(Long taskListId) {
        return taskListRepository
                .findById(taskListId)
                .map(taskListMapper::entityToModel)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found"));
    }

    public List<TaskList> execute() {
        return taskListRepository
                .findAll()
                .stream()
                .map(taskListMapper::entityToModel)
                .toList();
    }
}
