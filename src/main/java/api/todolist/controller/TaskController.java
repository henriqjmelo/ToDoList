package api.todolist.controller;

import api.todolist.entities.Task;
import api.todolist.services.BoardService;
import api.todolist.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/{boardId}/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private BoardService boardService;

    @GetMapping
    @Operation(summary = "Lista todas as tarefas de um quadro")
    public ResponseEntity<List<Task>> getTasks(@PathVariable("boardId") Long boardId) {
        return ResponseEntity.ok(boardService.getTasks(boardId));
    }

    @GetMapping("/{taskId}")
    @Operation(summary = "Seleciona uma tarefa específica de um quadro")
    public ResponseEntity<Task> getTask(@PathVariable("taskId") Long taskId) {
        return ResponseEntity.ok(taskService.getTask(taskId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria uma tarefa específica em um quadro")
    public ResponseEntity<Task> createTask(@PathVariable("boardId") Long boardId, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(boardId, task));
    }

    @PutMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Marca tarefa no quadro como concluida")
    public ResponseEntity<Task> doneTask(@PathVariable("taskId") Long taskId) {
        return ResponseEntity.ok(taskService.doneTask(taskId));
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta tarefa específica do quadro de tarefas")
    void deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

}
