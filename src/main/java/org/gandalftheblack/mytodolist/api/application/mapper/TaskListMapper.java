package org.gandalftheblack.mytodolist.api.application.mapper;

import org.gandalftheblack.mytodolist.api.domain.model.TaskList;
import org.gandalftheblack.mytodolist.api.infrastructure.entity.TaskListEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskListMapper {
    TaskList entityToModel(TaskListEntity entity);
    TaskListEntity modelToEntity(TaskList model);

    @AfterMapping
    default void linkTasks(@MappingTarget TaskListEntity taskListEntity) {
        if (taskListEntity.getTasks() != null) {
            taskListEntity.getTasks().forEach(task -> task.setTaskList(taskListEntity));
        }
    }
}
