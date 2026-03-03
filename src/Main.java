
public class Main{

    public static void main(String[] args) {

        // Create a customer with product details
        Customer customer1 = new Customer(1, "Jason", "Mathew", "jam32@gmail.com", "Danish", 2, 2.50);

        System.out.println("Customer Details:");
        System.out.println(customer1);

        // Calculate total cost for the product purchased
        double totalCost = customer1.getPrice() * customer1.getQuantity();

        System.out.println("\nTotal Cost: $" + totalCost);

        //Create two more customers with product details
        Customer customer2 = new Customer(2, "Ryan", "Abraham", "ryan@gmail.com", "Coffee", 1, 4.99);

        Customer customer3 = new Customer(3, "Lebron", "James", "theking@gmail.com", "Burger", 3, 26.75);

        System.out.println("\nAdditional Customers:");
        System.out.println(customer2);
        System.out.println(customer3);

        // Show total cost for each customer
        System.out.println("\nTotal Costs:");
        System.out.println("Customer 2: $" + (customer2.getPrice() * customer2.getQuantity()));
        System.out.println("Customer 3: $" + (customer3.getPrice() * customer3.getQuantity()));
    }
}
