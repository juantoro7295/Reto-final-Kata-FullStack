package co.com.sofka.crud.kata.service.impl;

import co.com.sofka.crud.kata.model.Todo;
import co.com.sofka.crud.kata.repository.TodoRepository;
import co.com.sofka.crud.kata.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Iterable<Todo> list() {
        return todoRepository.findAll();
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo update(Todo todo) {
        Todo todoIdExist = todoRepository.findById(todo.getId_todo()).orElse(null);
        todoIdExist.setName(todo.getName());
        todoIdExist.setCompleted(todo.getCompleted());
        return todoRepository.save(todoIdExist);
    }

    @Override
    public String delete(Long id) {
        todoRepository.deleteById(id);
        return "";
    }

    @Override
    public Optional<Todo> getId(Long id) {
        return todoRepository.findById(id);
    }
}
