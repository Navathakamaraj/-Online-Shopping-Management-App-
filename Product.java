public class Product {
    private int productId;
    private String productName;
    private double cost;
    private String description;
    private int rating;

    public Product(int productId, String productName, double cost, String description, int rating) {
        this.productId = productId;
        this.productName = productName;
        this.cost = cost;
        this.description = description;
        this.rating = rating;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %.2f Rs | %d stars | %s", productId, productName, cost, rating, description);
    }
}
