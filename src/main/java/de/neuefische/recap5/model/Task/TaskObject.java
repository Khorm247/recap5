package de.neuefische.recap5.model.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "TaskObjects")
public class TaskObject {
    String id;
    String description;
    String status;
    //String details;
}
