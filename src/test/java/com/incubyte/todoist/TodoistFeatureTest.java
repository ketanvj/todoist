package com.incubyte.todoist;


import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.List;

@MicronautTest
public class TodoistFeatureTest {

  @Inject
  @Client("/")
  HttpClient httpClient;

  @Test
  public void save_and_getAll_todo_list() {
    Todoist todoist = new Todoist("Get Vegetables", false);
    Todoist storedTodo = httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoist), Argument.of(Todoist.class));

    Long id = storedTodo.getId();
    Todoist getTodo = httpClient.toBlocking().retrieve(HttpRequest.GET("todos/" + id), Argument.of(Todoist.class));
    Assertions.assertThat(storedTodo.getDescription()).isEqualTo(getTodo.getDescription());
    Assertions.assertThat(storedTodo.getDescription()).isEqualTo("Get Vegetables");

//    //get all
//    Todoist todoVeg = new Todoist("Get Vegetables", false);
//    Todoist savedTodo2 = httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoVeg), Argument.of(Todoist.class));
//
//    List<Todoist> getAllTodo = httpClient.toBlocking().retrieve(HttpRequest.GET("todos/"), Argument.listOf(Todoist.class));
//    //Assertions.assertThat(getAllTodo.size()).isEqualTo(2);
//   // Assertions.assertThat(getAllTodo).containsExactly(savedTodo2,storedTodo);
//
//    //get open status
//     todoVeg = new Todoist("Get Vegetables", true);
//     savedTodo2 = httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoVeg), Argument.of(Todoist.class));
//     getAllTodo = httpClient.toBlocking().retrieve(HttpRequest.GET("todos/open"), Argument.listOf(Todoist.class));
//
//    System.out.println(getAllTodo.size());
//
//    Assertions.assertThat(getAllTodo).containsExactly(savedTodo2);


    //get close status
  //  getAllTodo = httpClient.toBlocking().retrieve(HttpRequest.GET("todos/closed"), Argument.listOf(Todoist.class));
   // Assertions.assertThat(getAllTodo.size()).isEqualTo(1);
  //  Assertions.assertThat(getAllTodo).containsExactly(savedTodo2);


  }

//  @Test
  public void save_todo_list() {
    Todoist todoist = new Todoist("Get Vegetables", false);
    Todoist storedTodo = httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoist), Argument.of(Todoist.class));
    Long id = storedTodo.getId();
    Todoist getTodo = httpClient.toBlocking().retrieve(HttpRequest.GET("todos/" + id), Argument.of(Todoist.class));
    Assertions.assertThat(storedTodo.getDescription()).isEqualTo(getTodo.getDescription());
    Assertions.assertThat(storedTodo.getDescription()).isEqualTo("Get Vegetables");
  }

  //@Test
  public void get_todo_list() {
    Todoist todoNonVeg = new Todoist("Get nonVegetables", false);
    Todoist savedTodo = httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoNonVeg), Argument.of(Todoist.class));
    Todoist todoVeg = new Todoist("Get Vegetables", false);
    Todoist savedTodo2 = httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoVeg), Argument.of(Todoist.class));
    List<Todoist> getAllTodo = httpClient.toBlocking().retrieve(HttpRequest.GET("todos/"), Argument.listOf(Todoist.class));
    Assertions.assertThat(getAllTodo.size()).isEqualTo(2);
    Assertions.assertThat(getAllTodo).containsExactly(savedTodo, savedTodo2);
  }
  //@Test
  public void get_all_todos_with_open_status() {
    Todoist todoNonVeg = new Todoist("Get nonVegetables", false);
    Todoist savedTodo1 = httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoNonVeg), Argument.of(Todoist.class));
    Todoist todoVeg = new Todoist("Get Vegetables", true);
    Todoist savedTodo2 = httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoVeg), Argument.of(Todoist.class));
    List<Todoist> getAllTodo = httpClient.toBlocking().retrieve(HttpRequest.GET("todos/open"), Argument.listOf(Todoist.class));

    System.out.println(getAllTodo.size());
    Assertions.assertThat(getAllTodo.size()).isEqualTo(1);
    Assertions.assertThat(getAllTodo).containsExactly(savedTodo2);
  }

  //@Test
  public void get_all_todos_with_closed_status() {
    Todoist todoNonVeg = new Todoist("Get nonVegetables", false);
    Todoist savedTodo1 = httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoNonVeg), Argument.of(Todoist.class));
    Todoist todoVeg = new Todoist("Get Vegetables", true);
    Todoist savedTodo2 = httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoVeg), Argument.of(Todoist.class));
    List<Todoist> getAllTodo = httpClient.toBlocking().retrieve(HttpRequest.GET("todos/closed"), Argument.listOf(Todoist.class));
    Assertions.assertThat(getAllTodo.size()).isEqualTo(1);
    Assertions.assertThat(getAllTodo).containsExactly(savedTodo2);
  }
}
