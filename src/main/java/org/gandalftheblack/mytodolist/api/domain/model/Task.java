package org.gandalftheblack.mytodolist.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long id;
    private String title;
    private boolean completed;
    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;
}
