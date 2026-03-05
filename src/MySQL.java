import java.sql.*;

public class MySQL {

    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/cafe";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "IST888IST888";

    private Connection connection;

    public MySQL() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to MySQL", e);
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public void create(Customer customer) {

        String sql = "INSERT INTO customers (id, firstName, lastName, email, product, quantity, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, customer.getId());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getLastName());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getProduct());
            ps.setInt(6, customer.getQuantity());
            ps.setDouble(7, customer.getPrice());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public void read(int id) {

        String sql = "SELECT id, firstName, lastName, email, product, quantity, price FROM customers WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Customer c = new Customer(
                            rs.getInt("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("email"),
                            rs.getString("product"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    );

                    System.out.println(c);

                } else {

                    System.out.println("MySQL: No customer found with id=" + id);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void update(Customer customer) {

        String sql = "UPDATE customers SET firstName = ?, lastName = ?, email = ?, product = ?, quantity = ?, price = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getProduct());
            ps.setInt(5, customer.getQuantity());
            ps.setDouble(6, customer.getPrice());
            ps.setInt(7, customer.getId());

            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("MySQL: Update did nothing (no matching id=" + customer.getId() + ")");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int id) {

        String sql = "DELETE FROM customers WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("MySQL: Delete did nothing (no matching id=" + id + ")");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


