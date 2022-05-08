package co.com.sofka.crud.kata.service;

import co.com.sofka.crud.kata.dto.TodoListDTO;
import java.util.List;

public interface TodoListService {
    //list
    List<TodoListDTO> list();

    //save
    TodoListDTO save(TodoListDTO todoListDTO);

    //update
    TodoListDTO update(TodoListDTO todoListDTO);

    //delete
    String delete(Long id);

    //get
    TodoListDTO getId(Long id);


}
