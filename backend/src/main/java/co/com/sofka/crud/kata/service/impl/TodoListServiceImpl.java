package co.com.sofka.crud.kata.service.impl;

import co.com.sofka.crud.kata.dto.TodoListDTO;
import co.com.sofka.crud.kata.model.TodoList;
import co.com.sofka.crud.kata.repository.TodoListRepository;
import co.com.sofka.crud.kata.service.TodoListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
     * Inyeccion de mapper
     */
    @Autowired
    private ModelMapper mapper;

    /**
     * Servicio para listar un TodoLIst
     *
     * @return list de TodoListDTO
     */
    @Override
    public List<TodoListDTO> list() {
        return todoListRepository
                .findAll()
                .stream()
                .map(todoList -> mapper.map(todoList, TodoListDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Servicio para guardar un TodoList
     *
     * @param todoListDTO
     * @return TodoListDTO
     */
    @Override
    public TodoListDTO save(TodoListDTO todoListDTO) {
        //DTO a Entity
        TodoList todoListEntity = mapper.map(todoListDTO, TodoList.class);
        //save
        TodoList todoListSave = todoListRepository.save(todoListEntity);
        //Entity a DTO
        TodoListDTO todoListReturn = mapper.map(todoListSave, TodoListDTO.class);

        return todoListReturn;
    }

    /**
     * Servicio para actualizar un TodoLIst
     *
     * @param todoListDTO
     * @return TodoListDTO
     */
    @Override
    public TodoListDTO update(TodoListDTO todoListDTO) {
        TodoList todoListIdExist = todoListRepository.findById(todoListDTO.getId_groupList()).orElse(null);
        todoListIdExist.setName(todoListDTO.getName());
        todoListRepository.save(todoListIdExist);
        //Entity a DTO
        TodoListDTO todoListDTOReturn = mapper.map(todoListIdExist, TodoListDTO.class);
        return todoListDTOReturn;
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
     * @return TodoLIstDTO
     */

    @Override
    public TodoListDTO getId(Long id) {
        TodoList todoListEntity = todoListRepository.findById(id).orElse(null);
        //Entity a DTO
        TodoListDTO todoListReturn = mapper.map(todoListEntity, TodoListDTO.class);
        return todoListReturn;
    }
}
