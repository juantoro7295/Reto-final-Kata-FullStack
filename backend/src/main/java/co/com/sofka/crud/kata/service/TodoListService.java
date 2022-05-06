package co.com.sofka.crud.kata.service;
import co.com.sofka.crud.kata.model.TodoList;

import java.util.Optional;

public interface TodoListService {
    //list
    Iterable<TodoList> list();

    //save
    TodoList save(TodoList todoList);

    //update
    TodoList update(TodoList todoList, Long id);

    //delete
    String delete(Long id);

    //get
    Optional<TodoList> getId(Long id);


}
