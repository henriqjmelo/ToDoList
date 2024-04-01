package api.todolist.controller;

import api.todolist.entities.Board;
import api.todolist.repository.BoardRepository;
import api.todolist.services.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping
    @Operation(summary = "Lista todos os quadros de tarefas da aplicação")
    public ResponseEntity<List<Board>> getBoards() {
        return ResponseEntity.ok(boardRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar quadro de tarefas")
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        return ResponseEntity.ok(boardService.createBoard(board));
    }

    @GetMapping("/{boardId}")
    @Operation(summary = "Lista apenas um quadro de tarefas da aplicação")
    public ResponseEntity<Board> getBoard(@PathVariable("boardId") Long boardId) {
        return ResponseEntity.ok(boardService.getBoard(boardId));
    }
}
