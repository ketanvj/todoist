package com.incubyte.todoist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoistControllerShould {

  @Mock
  TodoistService todoistService;
  private TodoistController todoistController;

  @BeforeEach
  void setUp() {
    todoistController = new TodoistController(todoistService);
  }

  @Test
  public void invoke_todoistService_save() {
    Todoist todoist = new Todoist(anyString(), false);

    todoistController.save(todoist);

    verify(todoistService).save(todoist);
  }

  @Test
  public void invoke_todoistService_get() {
    todoistController.get(1L);
    verify(todoistService).getById(1L);
  }

  @Test
  public void invoke_todoistService_get_all() {
    todoistController.getAllTodos();
    verify(todoistService).getAllTodos();
  }

  @Test
  public void invoke_todoistService_get_all_todos_with_open_status() {
    todoistController.getAllTodosWithOpenStatus();
    verify(todoistService).getAllTodosByStatus("open");
  }
  @Test
  public void invoke_todoistService_get_all_todos_with_closed_status() {
    todoistController.getAllTodosWithClosedStatus();
    verify(todoistService).getAllTodosByStatus("closed");
  }
}
