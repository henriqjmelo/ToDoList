package api.todolist.services;

import api.todolist.entities.Board;
import api.todolist.entities.Task;
import api.todolist.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Task> getTasks(Long boardId) {
        Board board = getBoard(boardId);
        return board.getTasks();
    }

    @SneakyThrows
    @Transactional
    public Board createBoard(Board board) {
        if (boardRepository.findByName(board.getName()).isEmpty()) {
            return boardRepository.save(board);
        }
        throw new Exception("Board ja existe");
    }

    @SneakyThrows
    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new Exception("Board não existe"));
    }
}
