package co.com.sofka.crud.kata.controller;

import co.com.sofka.crud.kata.model.TodoList;
import co.com.sofka.crud.kata.service.impl.TodoListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
     * @return list
     */
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        Iterable<TodoList> list = todoListServiceImpl.list();
        return (list != null)
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>("No hay nada en la lista", HttpStatus.NOT_FOUND);
    }

    /**
     * Guadar un todoList
     *
     * @param todoList
     * @return todoList
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TodoList todoList) {
        return (todoList != null)
                ? new ResponseEntity<>(todoListServiceImpl.save(todoList), HttpStatus.OK)
                : new ResponseEntity<>("Cuerpo del todo Incorrecto", HttpStatus.NOT_FOUND);
    }

    /**
     * Actualizar un todoList
     *
     * @param todoList
     * @return todoList
     */
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody TodoList todoList) {
        Optional<TodoList> todoExist = todoListServiceImpl.getId(todoList.getId_groupList());
        return (todoExist.isPresent())
                ? new ResponseEntity<>(todoListServiceImpl.update(todoList), HttpStatus.OK)
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
        Optional<TodoList> todoExist = todoListServiceImpl.getId(id);
        return (todoExist.isPresent())
                ? new ResponseEntity<>(todoListServiceImpl.delete(id), HttpStatus.NO_CONTENT)
                : new ResponseEntity<>("No existe lo que desea eliminar", HttpStatus.NOT_FOUND);

    }

    /**
     * Obtener por id
     *
     * @param id
     * @return mensaje
     */

    @GetMapping("/getId/{id}")
    public ResponseEntity<?> getId(@PathVariable("id") Long id) {
        return (id != null)
                ? new ResponseEntity<>(todoListServiceImpl.getId(id), HttpStatus.OK)
                : new ResponseEntity<>("No existe el id que buscas", HttpStatus.NOT_FOUND);

    }


}
