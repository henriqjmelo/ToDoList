package api.todolist.services;

import api.todolist.entities.Board;
import api.todolist.entities.Task;
import api.todolist.repository.BoardRepository;
import api.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private BoardRepository boardRepository;

    public Task getTask(Long id) {
        return getTaskOrThrow(id);
    }

    @SneakyThrows
    @Transactional
    public Task createTask(Long boardId, Task task) {
        Optional<Board> boardResult = boardRepository.findById(boardId);

        if (boardResult.isEmpty()) {
            throw new Exception("Board não existe");
        }

        task.setBoard(boardResult.get());

        return taskRepository.save(task);
    }

    @Transactional
    public Task doneTask(Long taskId) {
        Task task = getTaskOrThrow(taskId);

        task.setDone(true);

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long taskId) {
        getTaskOrThrow(taskId);
        taskRepository.deleteById(taskId);
    }

    @SneakyThrows
    private Task getTaskOrThrow(Long taskId) {
        Optional<Task> taskResult = taskRepository.findById(taskId);

        if (taskResult.isEmpty()) {
            throw new Exception("Task não existe");
        }

        return taskResult.get();
    }
}
