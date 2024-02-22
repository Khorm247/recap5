package de.neuefische.recap5.model.Task.DTOs;

import de.neuefische.recap5.model.Task.TaskStatus;

public record TaskDto(
        String description,
        TaskStatus status
) {}
