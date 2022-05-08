package co.com.sofka.crud.kata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  DTO de TodoLIst
 *
 * @author Juan Pablo Toro Hurtado.
 * @version 1.0.0
 * @since 1.0.0
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoListDTO {
    private Long id_groupList;

    private String name;
}
