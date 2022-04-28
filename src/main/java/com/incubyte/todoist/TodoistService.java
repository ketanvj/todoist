package com.incubyte.todoist;

import jakarta.inject.Singleton;

import java.util.ArrayList;
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

  public Iterable<Todoist> getAllTodos() {
    return todoistRepository.findAllOrderById();
  }

  public Iterable<Todoist> getAllTodosByStatus(String status) {
    Iterable<Todoist> allTodos = getAllTodos();
    List<Todoist> filteredTodos = new ArrayList<>();

    for (Todoist todo : allTodos) {

      if (todo.isDone() == status.equals("closed")) {
        filteredTodos.add(todo);
      }

    }
    return filteredTodos;


  }
}
