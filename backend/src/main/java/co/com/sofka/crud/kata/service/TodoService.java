package co.com.sofka.crud.kata.service;

import co.com.sofka.crud.kata.dto.TodoDTO;
import java.util.List;


public interface TodoService {
    //list
    List<TodoDTO> list();

    //save
    TodoDTO save(TodoDTO todoDTO);

    //update
    TodoDTO update(TodoDTO todoDTO);

    //delete
    String delete(Long id);

    //get
    TodoDTO getId(Long id);


}
