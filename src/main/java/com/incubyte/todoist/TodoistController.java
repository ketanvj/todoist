package com.incubyte.todoist;

import io.micronaut.http.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller("todos/")
public class TodoistController {
    private final TodoistService todoistService;

    public TodoistController(TodoistService todoistService) {
           this.todoistService = todoistService;
    }

    @Post
    public Todoist save(@Body Todoist todoist) {
        return todoistService.save(todoist);

    }

    @Get("/{id}")
    public Optional<Todoist> get(Long id) {
        return todoistService.getById(id);
    }

    @Get
    public List<Todoist> getAllTodos() {
        return todoistService.getAllTodos();
    }
}
