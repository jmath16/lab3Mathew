

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String product;  // Name of product purchased
    private int quantity;  // Quantity of product purchased
    private double price; // Price of product purchased

    /**
     * Constructor to create a Customer object.
     *
     * @param id ID of the customer.
     * @param firstName First name of the customer
     * @param lastName Last name of the customer
     * @param email email address of the customer
     * @param product The name of the product purchased by the customer.
     * @param quantity The price of the product purchased
     * @param price The quantity of the product purchased
     */

    public Customer(int id, String firstName, String lastName,
                    String email, String product, int quantity, double price ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
    // Getter
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    // Setter
    public void setEmail(String email) { this.email = email; }
    public void setProduct(String product) { this.product = product; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    /**
     * Returns a string of the Customer object
     *
     * @return A string containing customer details including product info.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", product='" + product + '\'' +
                ", quantity='" + quantity +
                ", price=" + price +
                '}';
    }


}

