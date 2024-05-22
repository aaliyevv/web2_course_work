package To_Do_Application.To_Do_Application.controller;

import To_Do_Application.To_Do_Application.model.ToDo;
import To_Do_Application.To_Do_Application.service.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping
    public ToDo createToDo(@RequestBody ToDo todo) {
        return toDoService.saveToDo(todo);
    }

    @GetMapping
    public List<ToDo> getAllToDos() {
        return toDoService.listAll();
    }

    @PutMapping("/{id}")
    public ToDo completeToDo(@PathVariable String id, @RequestParam boolean isComplete) {
        return toDoService.updateToDo(id, isComplete);
    }

    @DeleteMapping("/{id}")
    public void deleteToDo(@PathVariable String id) {
        toDoService.deleteToDo(id);
    }

    @GetMapping("/person")
    public List<ToDo> getToDosByPerson(@RequestParam String person) {
        return toDoService.findByPerson(person);
    }
}
