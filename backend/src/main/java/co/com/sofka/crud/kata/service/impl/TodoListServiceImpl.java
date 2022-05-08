package co.com.sofka.crud.kata.service.impl;

import co.com.sofka.crud.kata.model.TodoList;
import co.com.sofka.crud.kata.repository.TodoListRepository;
import co.com.sofka.crud.kata.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicios del TodoList
 *
 * @author Juan Pablo Toro Hurtado.
 * @version 1.0.0
 * @since 1.0.0
 */

@Service
public class TodoListServiceImpl implements TodoListService {

    /**
     * Inyeccion de todoListRespoditory
     */
    @Autowired
    private TodoListRepository todoListRepository;

    /**
     * Servicio para listar un TodoLIst
     *
     * @return Iterable de TodoList
     */
    @Override
    public Iterable<TodoList> list() {
        return todoListRepository.findAll();
    }

    /**
     * Servicio para guardar un TodoList
     *
     * @param todoList
     * @return TodoList
     */
    @Override
    public TodoList save(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    /**
     * Servicio para actualizar un TodoLIst
     *
     * @param todoList
     * @return TodoList
     */
    @Override
    public TodoList update(TodoList todoList) {
        TodoList todoListIdExist = todoListRepository.findById(todoList.getId_groupList()).orElse(null);
        todoListIdExist.setName(todoList.getName());
        return todoListRepository.save(todoListIdExist);
    }

    /**
     * Servicio para eliminar un TodoList
     *
     * @param id
     * @return mensaje
     */
    @Override
    public String delete(Long id) {
        todoListRepository.deleteById(id);
        return "";
    }

    /**
     * Servicio para obtener un TodoList Por id
     *
     * @param id
     * @return TodoLIst
     */

    @Override
    public Optional<TodoList> getId(Long id) {
        return todoListRepository.findById(id);
    }
}
