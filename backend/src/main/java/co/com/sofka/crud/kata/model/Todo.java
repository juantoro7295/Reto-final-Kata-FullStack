package co.com.sofka.crud.kata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "completed")
    private Boolean completed;

    @ManyToOne(
            targetEntity = TodoList.class,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "id_list")
    @JsonBackReference
    private TodoList todolist;

}
