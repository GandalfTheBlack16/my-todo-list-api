package org.gandalftheblack.mytodolist.api.application.service;

import lombok.RequiredArgsConstructor;
import org.gandalftheblack.mytodolist.api.application.mapper.TaskListMapper;
import org.gandalftheblack.mytodolist.api.domain.model.TaskList;
import org.gandalftheblack.mytodolist.api.infrastructure.entity.TaskListEntity;
import org.gandalftheblack.mytodolist.api.infrastructure.repository.TaskListRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostTaskListUseCase {
    private final TaskListRepository taskListRepository;
    private final TaskListMapper taskListMapper;

    public TaskList execute(TaskList taskList) {
        TaskListEntity taskListEntity = taskListMapper.modelToEntity(taskList);
        return taskListMapper.entityToModel(
                taskListRepository.save(taskListEntity)
        );
    }

}
