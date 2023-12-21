package family_budget.model;

import java.util.Objects;

public class Product {
    private String product;
    private double price;
    private int quantity;

    public Product(String product, double price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Product)) return false;
        return Objects.equals(product, ((Product) object).product);
    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product='" + product + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
