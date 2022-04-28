package com.incubyte.todoist;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface TodoistRepository extends CrudRepository<Todoist, Long> {
}
