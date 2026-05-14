package atm;

import java.math.BigDecimal;

public interface DepositBox {
    void acceptDeposit(BigDecimal amount);
}
