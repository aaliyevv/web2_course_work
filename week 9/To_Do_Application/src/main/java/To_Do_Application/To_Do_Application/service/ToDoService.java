package To_Do_Application.To_Do_Application.service;

import To_Do_Application.To_Do_Application.model.ToDo;
import To_Do_Application.To_Do_Application.repository.ToDoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDo saveToDo(ToDo todo) {
        return toDoRepository.save(todo);
    }

    public List<ToDo> listAll() {
        return toDoRepository.findAll();
    }

    public ToDo updateToDo(String id, boolean isComplete) {
        ToDo todo = toDoRepository.findById(id).orElse(null);
        if (todo != null) {
            todo.setComplete(isComplete);
            return toDoRepository.save(todo);
        }
        return null;
    }

    public void deleteToDo(String id) {
        toDoRepository.deleteById(id);
    }

    public List<ToDo> findByPerson(String person) {
        return toDoRepository.findByPerson(person);
    }
}
