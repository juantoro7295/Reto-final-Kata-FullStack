package co.com.sofka.crud.kata.model;
import lombok.*;
import javax.persistence.*;
import java.util.List;
/**
 *  Modelo de TodoList
 *
 * @author Juan Pablo Toro Hurtado.
 * @version 1.0.0
 * @since 1.0.0
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GroupList")
public class TodoList {

    @Id
    @GeneratedValue
    private Long id_groupList;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_groupList") //id_groupList
    private List<Todo> todos;

}
