package hu.progmasters.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDetails {

    private Long id;
    private String accountNumber;
    private Double balance;
    private String userName;
}
