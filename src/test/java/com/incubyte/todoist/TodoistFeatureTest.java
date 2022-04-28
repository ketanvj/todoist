package com.incubyte.todoist;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@MicronautTest
public class TodoistFeatureTest {


    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    public void save_todo_list() {
        Todoist todoist = new Todoist();
        todoist.setDescription("Get Vegetables");
        Todoist storedTodo = httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoist), Argument.of(Todoist.class));
        Long id = storedTodo.getId();
        Todoist getTodo = httpClient.toBlocking().retrieve(HttpRequest.GET("todos/" + id), Argument.of(Todoist.class));
        Assertions.assertThat(storedTodo.getDescription()).isEqualTo(getTodo.getDescription());
        Assertions.assertThat(storedTodo.getDescription()).isEqualTo("Get Vegetables");
    }

    @Test
    public void get_todo_list() {
        Todoist todoist = new Todoist();
        todoist.setDescription("Get nonVegetables");
        httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoist), Argument.of(Todoist.class));
        todoist.setDescription("Get Vegetables");
        httpClient.toBlocking().retrieve(HttpRequest.POST("todos/", todoist), Argument.of(Todoist.class));
        List<Todoist> getAllTodo = httpClient.toBlocking().retrieve(HttpRequest.GET("todos/"), Argument.listOf(Todoist.class));
        Assertions.assertThat(getAllTodo.size()).isEqualTo(2);
    }
}
