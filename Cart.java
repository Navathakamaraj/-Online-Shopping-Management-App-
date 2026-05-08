import java.util.List;

public class Cart {
    private int productId;
    private int quantity;

    public Cart(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double calculateAmount(List<Product> products) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product.getCost() * quantity;
            }
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return String.format("Product ID: %d, Quantity: %d", productId, quantity);
    }
}
