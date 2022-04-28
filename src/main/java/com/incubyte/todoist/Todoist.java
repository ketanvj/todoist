package com.incubyte.todoist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Todoist {
  @Id
  @GeneratedValue
  private Long id;
  private String description;

  private  boolean done;

  public Todoist(String description, boolean done) {
    this.description = description;
    this.done = done;
  }
  public Todoist() {
  }

  public Long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public boolean isDone() {
    return done;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Todoist todoist = (Todoist) o;

    if (id != null ? !id.equals(todoist.id) : todoist.id != null) return false;
    return description != null ? description.equals(todoist.description) : todoist.description == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }
}
