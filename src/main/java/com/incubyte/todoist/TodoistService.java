package com.incubyte.todoist;

import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class TodoistService {

    private TodoistRepository todoistRepository;

    public TodoistService(TodoistRepository todoistRepository) {
        this.todoistRepository = todoistRepository;
    }

    public Todoist save(Todoist todoist) {
        return todoistRepository.save(todoist);

    }

    public Optional<Todoist> getById(Long id) {
        return todoistRepository.findById(id);
    }

    public List<Todoist> getAllTodos() {
        return (List<Todoist>) todoistRepository.findAll();
    }
}
