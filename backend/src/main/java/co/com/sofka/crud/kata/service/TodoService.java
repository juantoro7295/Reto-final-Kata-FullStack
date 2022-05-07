package co.com.sofka.crud.kata.service;

import co.com.sofka.crud.kata.model.Todo;

import java.util.Optional;

public interface TodoService {
    //list
    Iterable<Todo> list();

    //save
    Todo save(Todo todo);

    //update
    Todo update(Todo todo);

    //delete
    String delete(Long id);

    //get
    Optional<Todo> getId(Long id);


}
