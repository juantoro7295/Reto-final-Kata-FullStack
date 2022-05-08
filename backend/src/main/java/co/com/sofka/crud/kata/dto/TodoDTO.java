package co.com.sofka.crud.kata.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 *  DTO de todo
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
public class TodoDTO {

    private Long id_todo;

    @NotEmpty(message = "El nombre del todo No puede estar vacio")
    @Min(3)
    @Max(100)
    private String name;

    private Boolean completed;

    private Long id_groupList;
}
