package co.com.sofka.crud.kata.controller;

import co.com.sofka.crud.kata.model.Todo;
import co.com.sofka.crud.kata.service.impl.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/todo")
public class TodoController {

    @Autowired
    private TodoServiceImpl todoserviceImpl;

    @CrossOrigin
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        Iterable<Todo> list = todoserviceImpl.list();
        return (list != null)
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>("No hay nada en la lista", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Todo todo) {
        return (todo != null)
                ? new ResponseEntity<>(todoserviceImpl.save(todo), HttpStatus.OK)
                : new ResponseEntity<>("Cuerpo del todo Incorrecto", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Todo todo) {
        Optional<Todo> todoExist = todoserviceImpl.getId(todo.getId_todo());
        return (todoExist.isPresent())
                ? new ResponseEntity<>(todoserviceImpl.update(todo), HttpStatus.OK)
                : new ResponseEntity<>("no existe el Todo", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Optional<Todo> todoExist = todoserviceImpl.getId(id);
        return (todoExist.isPresent())
                ? new ResponseEntity<>(todoserviceImpl.delete(id), HttpStatus.NO_CONTENT)
                : new ResponseEntity<>("No existe lo que desea eliminar", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<?> getId(@PathVariable("id") Long id) {
        return (id != null)
                ? new ResponseEntity<>(todoserviceImpl.getId(id), HttpStatus.OK)
                : new ResponseEntity<>("No existe el id que buscas", HttpStatus.NOT_FOUND);

    }


}
