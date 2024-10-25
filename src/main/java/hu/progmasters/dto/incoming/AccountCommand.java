package hu.progmasters.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountCommand {

    private String accountNumber;
    private Double balance;

}
