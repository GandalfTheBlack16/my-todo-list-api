package org.gandalftheblack.mytodolist.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskList {
    private Long id;
    private String name;
    private List<Task> tasks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void upsertTask(Task task) {
        int index = taskExists(task.getId());
        if (index >= 0) {
            this.tasks.set(index, task);
        } else {
            this.tasks.add(task);
        }
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public void clearTasks() {
        this.tasks.clear();
    }

    // Check if a task with the given ID exists in the task list and return its index, or -1 if not found
    private int taskExists(Long taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(taskId)) {
                return i;
            }
        }
        return -1;
    }
}
