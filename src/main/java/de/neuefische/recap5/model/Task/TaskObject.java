package de.neuefische.recap5.model.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskObject {
    String id;
    String description;
    String status;
    //String details;
}
