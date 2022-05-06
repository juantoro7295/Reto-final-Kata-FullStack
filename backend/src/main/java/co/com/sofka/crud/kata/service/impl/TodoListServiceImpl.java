package co.com.sofka.crud.kata.service.impl;

import co.com.sofka.crud.kata.model.TodoList;
import co.com.sofka.crud.kata.repository.TodoListRepository;
import co.com.sofka.crud.kata.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoListServiceImpl implements TodoListService {
    @Autowired
    private TodoListRepository todoListRepository;

    @Override
    public Iterable<TodoList> list() {
        return todoListRepository.findAll();
    }

    @Override
    public TodoList save(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @Override
    public TodoList update(TodoList todoList, Long id) {
        TodoList todoListIdExist = todoListRepository.findById(id).orElse(null);
        todoListIdExist.setNameList(todoList.getNameList());
        return todoListRepository.save(todoListIdExist);
    }

    @Override
    public String delete(Long id) {
        todoListRepository.deleteById(id);
        return "";
    }

    @Override
    public Optional<TodoList> getId(Long id) {
        return todoListRepository.findById(id);
    }
}
