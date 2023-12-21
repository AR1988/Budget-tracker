package family_budget.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Purchase {

    private final String name;
    private final BigDecimal price;

    private final LocalDateTime purchaseDay;

    public Purchase(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.purchaseDay = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getPurchaseDay() {
        return purchaseDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(name, purchase.name) && Objects.equals(price, purchase.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", purchaseDay=" + purchaseDay +
                '}';
    }
}
