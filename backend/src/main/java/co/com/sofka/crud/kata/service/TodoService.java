package co.com.sofka.crud.kata.service;

import co.com.sofka.crud.kata.model.Todo;

public interface TodoService {
    //list
    Iterable<Todo> list();

    //save
    Todo save(Todo todo);

    //update
    Todo update(Todo todo, Long id);

    //delete
    String delete(Long id);

    //get
    Todo getId(Long id);


}
