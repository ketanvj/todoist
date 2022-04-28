package com.incubyte.todoist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoistServiceShould {

    @Mock
    TodoistRepository todoistRepository;

    @Test
    public void invoke_TodoistRepository_save() {
        TodoistService todoistService = new TodoistService(todoistRepository);
        Todoist todoist = new Todoist();
        todoistService.save(todoist);
        verify(todoistRepository).save(todoist);
    }

    @Test
    public void invoke_TodoistRepository_get() {
        TodoistService todoistService = new TodoistService(todoistRepository);

        todoistService.getById(1L);

        verify(todoistRepository).findById(1L);
    }

    @Test
    public void invoke_TodoistRepository_get_all() {
        TodoistService todoistService = new TodoistService(todoistRepository);

        todoistService.getAllTodos();

        verify(todoistRepository).findAll();
    }
}
