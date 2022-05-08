package co.com.sofka.crud.kata.service.impl;

import co.com.sofka.crud.kata.dto.TodoDTO;
import co.com.sofka.crud.kata.model.Todo;
import co.com.sofka.crud.kata.repository.TodoRepository;
import co.com.sofka.crud.kata.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
     * Inyeccion de mapper
     */
    @Autowired
    private ModelMapper mapper;

    /**
     * Servicio para listar un todo
     *
     * @return list de TodoDTO
     */
    @Override
    public List<TodoDTO> list() {

        return todoRepository
                .findAll()
                .stream()
                .map(todo -> mapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Servicio para guardar un Todo
     *
     * @param todoDTO
     * @return TodoDTO
     */
    @Override
    public TodoDTO save(TodoDTO todoDTO) {
        //DTO a Entity
        Todo todoEntity = mapper.map(todoDTO, Todo.class);
        //save
        Todo todoSave = todoRepository.save(todoEntity);
        //Entity a DTO
        TodoDTO todoReturn = mapper.map(todoSave, TodoDTO.class);

        return todoReturn;
    }

    /**
     * Servicio para actualizar un Todo
     *
     * @param todoDTO
     * @return todoDTO
     */
    @Override
    public TodoDTO update(TodoDTO todoDTO) {
        //DTO a Entity
        Todo todoEntity = mapper.map(todoDTO, Todo.class);
        //uodate
        Todo todoIdExist = todoRepository.findById(todoEntity.getId_todo()).orElse(null);
        todoIdExist.setName(todoEntity.getName());
        todoIdExist.setCompleted(todoEntity.getCompleted());
        todoRepository.save(todoIdExist);
        //Entity a DTO
        TodoDTO todoReturn = mapper.map(todoIdExist, TodoDTO.class);

        return todoReturn;
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
     * @return TodoDTO
     */
    @Override
    public TodoDTO getId(Long id) {
        Todo todoEntity = todoRepository.findById(id).orElse(null);
        //Entity a DTO
        TodoDTO todoReturn = mapper.map(todoEntity, TodoDTO.class);
        return todoReturn;
    }
}
