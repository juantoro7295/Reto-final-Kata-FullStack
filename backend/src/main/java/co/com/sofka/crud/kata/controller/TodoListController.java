package co.com.sofka.crud.kata.controller;

import co.com.sofka.crud.kata.dto.TodoListDTO;
import co.com.sofka.crud.kata.service.impl.TodoListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * ToDoList controlador - TodoListController
 *
 * @author Juan Pablo Toro Hurtado.
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/todolist")
public class TodoListController {

    /**
     * Inyeccion de todoListServiceImpl
     */
    @Autowired
    private TodoListServiceImpl todoListServiceImpl;


    /**
     * Listar un ToDoList
     *
     * @return listDTO
     */
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<TodoListDTO> list = todoListServiceImpl.list();
        return (list != null)
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>("No hay nada en la lista", HttpStatus.NOT_FOUND);
    }

    /**
     * Guadar un todoList
     *
     * @param todoListDTO
     * @return todoListDTO
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TodoListDTO todoListDTO) {
        return (todoListDTO != null)
                ? new ResponseEntity<>(todoListServiceImpl.save(todoListDTO), HttpStatus.OK)
                : new ResponseEntity<>("Cuerpo del todo Incorrecto", HttpStatus.NOT_FOUND);
    }

    /**
     * Actualizar un todoList
     *
     * @param todoListDTO
     * @return todoListDTO
     */
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody TodoListDTO todoListDTO) {
        TodoListDTO todoExist = todoListServiceImpl.getId(todoListDTO.getId_groupList());
        return (todoExist != null)
                ? new ResponseEntity<>(todoListServiceImpl.update(todoListDTO), HttpStatus.OK)
                : new ResponseEntity<>("no existe el todoList", HttpStatus.NOT_FOUND);
    }

    /**
     * Eliminar por id
     *
     * @param id
     * @return mensaje
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        TodoListDTO todoExist = todoListServiceImpl.getId(id);
        return (todoExist != null)
                ? new ResponseEntity<>(todoListServiceImpl.delete(id), HttpStatus.NO_CONTENT)
                : new ResponseEntity<>("No existe lo que desea eliminar", HttpStatus.NOT_FOUND);

    }

    /**
     * Obtener por id
     *
     * @param id
     * @return todoDTO
     */

    @GetMapping("/getId/{id}")
    public ResponseEntity<?> getId(@PathVariable("id") Long id) {
        return (id != null)
                ? new ResponseEntity<>(todoListServiceImpl.getId(id), HttpStatus.OK)
                : new ResponseEntity<>("No existe el id que buscas", HttpStatus.NOT_FOUND);

    }


}
