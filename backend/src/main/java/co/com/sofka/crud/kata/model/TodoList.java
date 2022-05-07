package co.com.sofka.crud.kata.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todoList")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nameList")
    private String nameList;

    @OneToMany(
            mappedBy = "todolist",
            targetEntity = Todo.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Todo> todos;
}
