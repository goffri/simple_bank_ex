package hu.progmasters.dto.outgoing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsWithAccounts {

    private Long id;
    private String name;
    private String email;
    private List<AccountListItem> accounts;
}
