package hu.progmasters.dto.outgoing;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDetails {

    private Long id;
    private String name;
    private String email;

}
