package org.gandalftheblack.mytodolist.api.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.gandalftheblack.mytodolist.api.application.service.AddTaskToListUseCase;
import org.gandalftheblack.mytodolist.api.application.service.GetTaskListUseCase;
import org.gandalftheblack.mytodolist.api.application.service.PostTaskListUseCase;
import org.gandalftheblack.mytodolist.api.domain.model.Task;
import org.gandalftheblack.mytodolist.api.domain.model.TaskList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task-lists")
@RequiredArgsConstructor
public class TaskListController {

    private final GetTaskListUseCase getTaskListUseCase;
    private final PostTaskListUseCase postTaskListUseCase;
    private final AddTaskToListUseCase addTaskToListUseCase;

    @GetMapping
    public ResponseEntity<List<TaskList>> getTasks() {
        List<TaskList> taskLists = getTaskListUseCase.execute();
        if (taskLists.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(taskLists);
    }

    @GetMapping("/{taskListId}")
    public ResponseEntity<TaskList> getTaskListById(@PathVariable Long taskListId) {
        TaskList taskList = getTaskListUseCase.execute(taskListId);
        return ResponseEntity.ok(taskList);
    }

    @PostMapping
    public ResponseEntity<TaskList> addTaskList(@RequestBody TaskList taskList) {
        TaskList savedTaskList = postTaskListUseCase.execute(taskList);
        return ResponseEntity.ok(savedTaskList);
    }

    @PutMapping("/{taskListId}")
    public ResponseEntity<TaskList> updateTaskList(@PathVariable Long taskListId, @RequestBody TaskList taskList) {
        TaskList updatedTaskList = postTaskListUseCase.execute(taskListId, taskList);
        return ResponseEntity.ok(updatedTaskList);
    }

    @PutMapping("/{taskListId}/tasks")
    public ResponseEntity<TaskList> updateTaskList(@PathVariable Long taskListId, @RequestBody Task task) {
        TaskList updatedTaskList = addTaskToListUseCase.execute(taskListId, task);
        return ResponseEntity.ok(updatedTaskList);
    }
}
