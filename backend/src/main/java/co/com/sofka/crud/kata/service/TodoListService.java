package co.com.sofka.crud.kata.service;
import co.com.sofka.crud.kata.model.TodoList;

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
    TodoList getId(Long id);


}
