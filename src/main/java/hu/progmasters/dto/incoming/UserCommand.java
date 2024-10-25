package hu.progmasters.dto.incoming;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCommand {

    @NotBlank
    private String name;

    @NotBlank
    private String email;
}
