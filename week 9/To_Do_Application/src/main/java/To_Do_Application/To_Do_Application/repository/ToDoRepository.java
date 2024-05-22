package To_Do_Application.To_Do_Application.repository;

import To_Do_Application.To_Do_Application.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
    List<ToDo> findByPerson(String person);
}
