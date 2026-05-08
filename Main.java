import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ADMIN_PASSWORD = "admin123";
    private static final int MAX_PASSWORD_ATTEMPTS = 3;

    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();

        printHeader();
        boolean running = true;

        while (running) {
            printMainMenu();
            int option = readInt();
            switch (option) {
                case 1:
                    if (authenticateAdmin()) {
                        runAdminMenu(productList);
                    } else {
                        System.out.println("Access denied. Returning to main menu.");
                    }
                    break;
                case 2:
                    runUserMenu(productList, cartList);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Choose 1, 2, or 3.");
            }
        }

        printFooter();
    }

    private static void printMainMenu() {
        printLine();
        System.out.println("Main Menu");
        System.out.println("1. Admin login");
        System.out.println("2. User menu");
        System.out.println("3. Exit");
        System.out.print("Select an option: ");
    }

    private static boolean authenticateAdmin() {
        printLine();
        System.out.println("Admin authentication required.");

        for (int attempt = 1; attempt <= MAX_PASSWORD_ATTEMPTS; attempt++) {
            System.out.print("Enter admin password: ");
            String inputPassword = scanner.nextLine().trim();
            if (ADMIN_PASSWORD.equals(inputPassword)) {
                System.out.println("Access granted.");
                return true;
            }
            System.out.printf("Incorrect password (%d/%d).%n", attempt, MAX_PASSWORD_ATTEMPTS);
        }

        System.out.println("Maximum password attempts exceeded.");
        return false;
    }

    private static void runAdminMenu(List<Product> productList) {
        boolean adminRunning = true;
        while (adminRunning) {
            printLine();
            System.out.println("Admin Menu");
            System.out.println("1. Add product");
            System.out.println("2. View products");
            System.out.println("3. Delete product");
            System.out.println("4. Back to main menu");
            System.out.print("Choose an option: ");

            int choice = readInt();
            switch (choice) {
                case 1:
                    addProduct(productList);
                    break;
                case 2:
                    showProducts(productList);
                    break;
                case 3:
                    deleteProduct(productList);
                    break;
                case 4:
                    adminRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
            }
        }
    }

    private static void runUserMenu(List<Product> productList, List<Cart> cartList) {
        boolean userRunning = true;
        while (userRunning) {
            printLine();
            System.out.println("User Menu");
            System.out.println("1. View products");
            System.out.println("2. Add item to cart");
            System.out.println("3. Calculate bill");
            System.out.println("4. Back to main menu");
            System.out.print("Choose an option: ");

            int choice = readInt();
            switch (choice) {
                case 1:
                    showProducts(productList);
                    break;
                case 2:
                    addToCart(productList, cartList);
                    break;
                case 3:
                    printBill(productList, cartList);
                    break;
                case 4:
                    userRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, 3, or 4.");
            }
        }
    }

    private static void addProduct(List<Product> productList) {
        printLine();
        System.out.print("Product ID: ");
        int id = readInt();

        System.out.print("Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Cost (Rs): ");
        double cost = readDouble();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Rating (1-5): ");
        int rating = readInt();

        Product product = new Product(id, name, cost, description, rating);
        productList.add(product);
        System.out.println("Product added successfully.");
    }

    private static void deleteProduct(List<Product> productList) {
        if (productList.isEmpty()) {
            System.out.println("No products available to delete.");
            return;
        }

        showProducts(productList);
        System.out.print("Enter Product ID to delete: ");
        int id = readInt();

        boolean removed = productList.removeIf(product -> product.getProductId() == id);
        if (removed) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void showProducts(List<Product> productList) {
        printLine();
        if (productList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("Available Products:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    private static void addToCart(List<Product> productList, List<Cart> cartList) {
        if (productList.isEmpty()) {
            System.out.println("No products available. Ask the admin to add products first.");
            return;
        }

        showProducts(productList);
        System.out.print("Enter Product ID to add to cart: ");
        int productId = readInt();

        System.out.print("Enter quantity: ");
        int quantity = readInt();

        cartList.add(new Cart(productId, quantity));
        System.out.println("Item added to cart.");
    }

    private static void printBill(List<Product> productList, List<Cart> cartList) {
        printLine();
        if (cartList.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        double totalAmount = 0.0;
        System.out.println("Cart Summary:");
        for (Cart cart : cartList) {
            double amount = cart.calculateAmount(productList);
            System.out.printf("%s | Amount: %.2f Rs%n", cart, amount);
            totalAmount += amount;
        }

        System.out.println("---------------------------");
        System.out.printf("Total payable amount: %.2f Rs%n", totalAmount);
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Please enter a valid number: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private static double readDouble() {
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print("Please enter a valid amount: ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    private static void printHeader() {
        System.out.println("====================================");
        System.out.println("   Online Shopping Management App   ");
        System.out.println("====================================");
    }

    private static void printFooter() {
        System.out.println("====================================");
        System.out.println("Thank you for using the system.");
        System.out.println("====================================");
    }

    private static void printLine() {
        System.out.println("------------------------------------");
    }
}
