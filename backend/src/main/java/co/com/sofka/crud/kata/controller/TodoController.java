package co.com.sofka.crud.kata.controller;

import co.com.sofka.crud.kata.dto.TodoDTO;
import co.com.sofka.crud.kata.service.impl.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



/**
 * ToDo controlador - TodoController
 *
 * @author Juan Pablo Toro Hurtado.
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/todo")
public class TodoController {

    /**
     * inyeccion todoserviceImpl
     */
    @Autowired
    private TodoServiceImpl todoserviceImpl;

    /**
     * Listar los ToDos
     *
     * @return list
     */

    @CrossOrigin
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<TodoDTO> list = todoserviceImpl.list();
        return (list != null)
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>("No hay nada en la lista", HttpStatus.NOT_FOUND);
    }

    /**
     * Guardar un ToDo
     *
     * @param todoDTO
     * @return todoDTO
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TodoDTO todoDTO) {
        return (todoDTO != null)
                ? new ResponseEntity<>(todoserviceImpl.save(todoDTO), HttpStatus.OK)
                : new ResponseEntity<>("Cuerpo del todo Incorrecto", HttpStatus.NOT_FOUND);
    }

    /**
     * Actualizar un ToDo
     *
     * @param todoDTO
     * @return todoDTO
     */
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody TodoDTO todoDTO) {
        TodoDTO todoExist = todoserviceImpl.getId(todoDTO.getId_todo());
        return (todoExist != null)
                ? new ResponseEntity<>(todoserviceImpl.update(todoDTO), HttpStatus.OK)
                : new ResponseEntity<>("no existe el Todo", HttpStatus.NOT_FOUND);
    }

    /**
     * Eliminar ToDO por id
     *
     * @param id
     * @return mensaje
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        TodoDTO todoExist = todoserviceImpl.getId(id);
        return (todoExist != null)
                ? new ResponseEntity<>(todoserviceImpl.delete(id), HttpStatus.NO_CONTENT)
                : new ResponseEntity<>("No existe lo que desea eliminar", HttpStatus.NOT_FOUND);

    }

    /**
     * Obtener un ToDo por id
     *
     * @param id
     * @return TodoDTO
     */
    @GetMapping("/getId/{id}")
    public ResponseEntity<?> getId(@PathVariable("id") Long id) {
        return (id != null)
                ? new ResponseEntity<>(todoserviceImpl.getId(id), HttpStatus.OK)
                : new ResponseEntity<>("No existe el id que buscas", HttpStatus.NOT_FOUND);

    }


}
