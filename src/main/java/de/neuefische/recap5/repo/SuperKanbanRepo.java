package de.neuefische.recap5.repo;

import de.neuefische.recap5.model.Task.TaskObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperKanbanRepo extends MongoRepository<TaskObject, String> {
}
