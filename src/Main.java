public class Main {

    public static void main(String[] args) {

        // Create database objects
        MySQL mysql = new MySQL();
        Mongo mongo = new Mongo();

        // Create customers
        Customer c1 = new Customer(4, "Jason", "Mathew", "jam32@gmail.com", "Danish", 2, 3.00);
        Customer c2 = new Customer(5, "Ryan", "Abraham", "ryan@gmail.com", "Coffee", 1, 4.00);
        Customer c3 = new Customer(3, "LeBron", "James", "TheKing@gmail.com", "Sandwich", 4, 22.00);

        // -------------------------
        // CREATE
        // -------------------------

        mysql.create(c1);
        mysql.create(c2);
        mysql.create(c3);

        mongo.create(c1);
        mongo.create(c2);
        mongo.create(c3);

        // -------------------------
        // READ
        // -------------------------

        mysql.read(1);
        mongo.read(1);

        // -------------------------
        // UPDATE
        // -------------------------

        c1.setProduct("Danish");
        c1.setPrice(3.00);

        mysql.update(c1);
        mongo.update(c1);

        // -------------------------
        // DELETE
        // -------------------------

        mysql.delete(3);
        mongo.delete(3);

        // Close connections
        mysql.close();
        mongo.close();
    }
}
