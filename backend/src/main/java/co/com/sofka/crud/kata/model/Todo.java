package co.com.sofka.crud.kata.model;
import lombok.*;
import javax.persistence.*;

/**
 *  Modelo de Todo
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
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue
    private Long id_todo;

    private String name;

    private Boolean completed;

    private Long id_groupList;

}
