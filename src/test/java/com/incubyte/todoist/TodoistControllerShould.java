package com.incubyte.todoist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoistControllerShould {

    @Mock
    TodoistService todoistService;

    @Test
    public void invoke_todoistService_save() {
        TodoistController todoistController = new TodoistController(todoistService);
        Todoist todoist = new Todoist();

        todoistController.save(todoist);

        verify(todoistService).save(todoist);
    }

    @Test
    public void invoke_todoistService_get() {
        TodoistController todoistController = new TodoistController(todoistService);

        todoistController.get(1L);

        verify(todoistService).getById(1L);
    }

    @Test
    public void invoke_todoistService_get_all() {
        TodoistController todoistController = new TodoistController(todoistService);

        todoistController.getAllTodos();

        verify(todoistService).getAllTodos();
    }
}
