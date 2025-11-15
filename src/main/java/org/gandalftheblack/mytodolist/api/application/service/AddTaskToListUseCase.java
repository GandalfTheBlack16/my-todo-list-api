package org.gandalftheblack.mytodolist.api.application.service;

import lombok.RequiredArgsConstructor;
import org.gandalftheblack.mytodolist.api.application.mapper.TaskListMapper;
import org.gandalftheblack.mytodolist.api.domain.model.Task;
import org.gandalftheblack.mytodolist.api.domain.model.TaskList;
import org.gandalftheblack.mytodolist.api.infrastructure.repository.TaskListRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddTaskToListUseCase {
    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;

    public TaskList execute(Long taskListId, Task task) {
        TaskList taskList = taskListRepository
                .findById(taskListId)
                .map(taskListMapper::entityToModel)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found"));

        taskList.upsertTask(task);

        return taskListMapper.entityToModel(
                taskListRepository.save(
                        taskListMapper.modelToEntity(taskList)
                )
        );
    }
}
