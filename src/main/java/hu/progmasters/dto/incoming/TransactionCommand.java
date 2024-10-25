package hu.progmasters.dto.incoming;

import hu.progmasters.domain.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransactionCommand {

    private Double amount;
    private TransactionType transactionType;
}
