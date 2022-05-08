package co.com.sofka.crud.kata.service.impl;

import co.com.sofka.crud.kata.model.Todo;
import co.com.sofka.crud.kata.repository.TodoRepository;
import co.com.sofka.crud.kata.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicios del Todo
 *
 * @author Juan Pablo Toro Hurtado.
 * @version 1.0.0
 * @since 1.0.0
 */

@Service
public class TodoServiceImpl implements TodoService {

    /**
     * inyeccion todoRepository
     */
    @Autowired
    private TodoRepository todoRepository;

    /**
     * Servicio para listar un todo
     *
     * @return Iterable de Todo
     */
    @Override
    public Iterable<Todo> list() {
        return todoRepository.findAll();
    }

    /**
     * Servicio para guardar un Todo
     *
     * @param todo
     * @return Todo
     */
    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    /**
     * Servicio para actualizar un Todo
     *
     * @param todo
     * @return todo
     */
    @Override
    public Todo update(Todo todo) {
        Todo todoIdExist = todoRepository.findById(todo.getId_todo()).orElse(null);
        todoIdExist.setName(todo.getName());
        todoIdExist.setCompleted(todo.getCompleted());
        return todoRepository.save(todoIdExist);
    }

    /**
     * Servicio para eliminar un Todo
     *
     * @param id
     * @return mensaje
     */
    @Override
    public String delete(Long id) {
        todoRepository.deleteById(id);
        return "";
    }

    /**
     * Servicio para obtener un Todo por id
     *
     * @param id
     * @return Todo
     */
    @Override
    public Optional<Todo> getId(Long id) {
        return todoRepository.findById(id);
    }
}
