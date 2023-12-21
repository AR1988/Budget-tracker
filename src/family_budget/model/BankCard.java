package family_budget.model;

import java.math.BigDecimal;
import java.util.Objects;

public class BankCard {

    private final String name;
    private BigDecimal balance;

    public BankCard(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof BankCard)) return false;
        return Objects.equals(name, ((BankCard) object).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
