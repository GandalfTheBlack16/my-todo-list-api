package org.gandalftheblack.mytodolist.api.infrastructure.repository;

import org.gandalftheblack.mytodolist.api.infrastructure.entity.TaskListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskListEntity, Long> {
}
