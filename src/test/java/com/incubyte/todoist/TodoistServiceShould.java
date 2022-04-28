package com.incubyte.todoist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoistServiceShould {
  private TodoistService todoistService;
  @Mock
  TodoistRepository todoistRepository;

  @BeforeEach
  void setUp() {
    todoistService = new TodoistService(todoistRepository);

  }

  @Test
  public void invoke_TodoistRepository_save() {
    Todoist todoist = new Todoist(anyString(), false);
    todoistService.save(todoist);
    verify(todoistRepository).save(todoist);
  }

  @Test
  public void invoke_TodoistRepository_get() {
    todoistService.getById(1L);
    verify(todoistRepository).findById(1L);
  }

  @Test
  public void invoke_TodoistRepository_get_all() {
    todoistService.getAllTodos();
    verify(todoistRepository).findAllOrderById();
  }

  @Test
  public void invoke_TodoistRepository_get_all_todos_with_status_and_return_open_status_todos() {

    Todoist todo1 = new Todoist("Dummy Todo 1", false);
    Todoist todo2 = new Todoist("Dummy Todo 2", true);
    Todoist todo3 = new Todoist("Dummy Todo 3", false);
    Todoist todo4 = new Todoist("Dummy Todo 4", true);

    List<Todoist> todos = new ArrayList<>();
    todos.add(todo1);
    todos.add(todo2);
    todos.add(todo3);
    todos.add(todo4);

    when(todoistRepository.findAllOrderById()).thenReturn(todos);

    Iterable<Todoist> openTodos = todoistService.getAllTodosByStatus("open");
    verify(todoistRepository).findAllOrderById();
    assertThat(openTodos).size().isEqualTo(2);
    assertThat(openTodos).containsExactly(todo1, todo3);
  }

  @Test
  public void invoke_TodoistRepository_get_all_todos_with_status_and_return_closed_status_todos() {

    Todoist todo1 = new Todoist("Dummy Todo 1", false);
    Todoist todo2 = new Todoist("Dummy Todo 2", true);
    Todoist todo3 = new Todoist("Dummy Todo 3", false);
    Todoist todo4 = new Todoist("Dummy Todo 4", true);

    List<Todoist> todos = new ArrayList<>();
    todos.add(todo1);
    todos.add(todo2);
    todos.add(todo3);
    todos.add(todo4);

    when(todoistRepository.findAllOrderById()).thenReturn(todos);

    Iterable<Todoist> openTodos = todoistService.getAllTodosByStatus("closed");
    verify(todoistRepository).findAllOrderById();
    assertThat(openTodos).size().isEqualTo(2);
    assertThat(openTodos).containsExactly(todo2, todo4);
  }
}
