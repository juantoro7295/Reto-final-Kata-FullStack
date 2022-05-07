package co.com.sofka.crud.kata.model;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todoList") //GroupList
public class TodoList {

    @Id
    @GeneratedValue
    private Long id_todolist; // id_groupList

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "listGroup") //id_groupList
    private List<Todo> todos;




//    @OneToMany(
//            mappedBy = "todolist",
//            targetEntity = Todo.class,
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.REMOVE)
//    @JsonManagedReference
//    private List<Todo> todos;
}
