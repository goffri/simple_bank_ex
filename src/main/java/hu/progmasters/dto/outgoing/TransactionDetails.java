package hu.progmasters.dto.outgoing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDetails {

    private Long id;
    private Double amount;
    private String transactionType;
    private String accountId;
}
