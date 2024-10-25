package hu.progmasters.dto.outgoing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AccountListItem {

    private Long id;
    private String accountNumber;
    private Double balance;
    private String userName;
    private List<TransactionListItem> transactions;
}
