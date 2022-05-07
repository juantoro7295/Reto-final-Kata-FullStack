package co.com.sofka.crud.kata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue
    private Long id_todo;

    private String name;

    private Boolean completed;

    private Long listGroup;  // groupList

//    @ManyToOne(
//            targetEntity = TodoList.class,
//            fetch = FetchType.LAZY,
//            optional = false
//    )
//    @JoinColumn(name = "id_list")
//    @JsonBackReference


}
