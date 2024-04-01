package api.todolist.pojo;

import org.springframework.http.HttpStatus;

public record ExceptionPojo(String message, HttpStatus httpStatus) {}
