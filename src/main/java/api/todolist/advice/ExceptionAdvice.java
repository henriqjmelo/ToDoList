package api.todolist.advice;

import api.todolist.pojo.ExceptionPojo;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ExceptionPojo> handleAccessDeniedException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionPojo(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }
}


