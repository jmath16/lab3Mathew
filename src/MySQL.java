

//import java.sql.Connection;
import org.example.Customer;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;



public class MySQL {

    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/cafe";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "IST888IST888";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Create (Insert) customers
            insertCustomer(connection, 1, "Jason", "Mathew", "jam32@gmail.com", "Danish", 2, 2.50);
            insertCustomer(connection, 2, "Ryan", "Abraham", "ryan@gmail.com", "Coffee", 1, 4.99);
            insertCustomer(connection, 3, "Lebron", "James", "theking@gmail.com", "Burger", 3, 26.75);

            // Read all customers
            List<Customer> customers = getAllCustomers(connection);
            System.out.println("Customers after insertion:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }

            // Update a customer email
            updateCustomerEmail(connection, 2, "ryan16@gmail.com");

            // Read all customers after update
            customers = getAllCustomers(connection);
            System.out.println("\nCustomers after update:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }

            //Delete a customer
            deleteCustomer(connection, 2);

            //Read all customers after deletion
            customers = getAllCustomers(connection);
            System.out.println("\nCustomers after deletion:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inserts a customer with product info into the database
     */
    private static void insertCustomer(Connection connection,
                                       int id,
                                       String firstName,
                                       String lastName,
                                       String email,
                                       String product,
                                       int quantity,
                                       double price) throws SQLException {
        String sql = "INSERT INTO Customers (id, firstName, lastName, email, product, quantity, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, email);
            ps.setString(5, product);
            ps.setInt(6, quantity);
            ps.setDouble(7, price);
            ps.executeUpdate();

        }
    }

    /**
     * Retrieves all customers from the customers table.
     *
     * @param connection The database connection.
     * @return A list of Customer objects.
     */
    private static List<Customer> getAllCustomers(Connection connection) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id, firstName, lastName, email, product, quantity, price FROM Customers";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String product = rs.getString("product");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                customers.add(new Customer(id, firstName, lastName, email, product, quantity, price));

            }
        }
        return customers;
    }

    /**
     * Updates the email address of a customer in the databse.
     *
     * @param connection The active database connection.
     * @param id The ID of the customer to update.
     * @param newEmail The new email address.
     */
    private static void updateCustomerEmail(Connection connection, int id, String newEmail) throws SQLException {
        String sql = "UPDATE Customers SET email = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newEmail);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    /**
     * Deletes a customer from the customers table.
     *
     * @param connection The active database connection
     * @param id The ID of the customer to delete.
     */
    private static void deleteCustomer(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM Customers WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

}
