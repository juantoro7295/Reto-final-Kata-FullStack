package co.com.sofka.crud.kata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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
@Validated
public class TodoListDTO {
    private Long id_groupList;

    @NotEmpty(message = "El nombre de la lista no puede ser vacia")
    @Min(3)
    @Max(100)
    private String name;
}
