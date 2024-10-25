package hu.progmasters.dto.outgoing;

import hu.progmasters.domain.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionListItem {

    private Long id;
    private Double amount;
    private TransactionType transactionType;
}
