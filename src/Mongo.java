

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

/**
 * CRUD operations for MongoDB for Customer objects
 */
public class Mongo {

    /**
     * Creating, reading, updating, and deleting Customer documents in MongoDB.
     */
    public static void main(String[] args) {

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            // Access the database and collection
            MongoDatabase database = mongoClient.getDatabase("cafe");
            MongoCollection<Document> collection = database.getCollection("customers");

            //CREATE (Insert) Customers
            Document customer1 = new Document()
                    .append("firstName", "Jason")
                    .append("lastName", "Mathew")
                    .append("email", "jam32@gmail.com")
                    .append("product", "Danish")
                    .append("quantity", 2)
                    .append("price", 2.50);
            Document customer2 = new Document()
                    .append("firstName", "Ryan")
                    .append("lastName", "Abraham")
                    .append("email", "ryan@gmail.com")
                    .append("product", "Coffee")
                    .append("quantity", "1")
                    .append("price", "4.99");
            Document customer3 = new Document()
                    .append("firstName", "Lebron")
                    .append("lastName", "James")
                    .append("email", "theking@gmail.com")
                    .append("product", "Burger")
                    .append("quantity", "3")
                    .append("price", "26.75");

            collection.insertOne(customer1);
            collection.insertOne(customer2);
            collection.insertOne(customer3);

            // READ (Print all customers)
            System.out.println("Customers after insertion:");
            printAllCustomers(collection);

            // UPDATE (Change Ryan's email)
            Document updatedCustomer = new Document("$set", new Document("email", "ryan16@gmail.com"));
            collection.updateOne(new Document("name", "Ryan Abraham"), updatedCustomer);

            System.out.println("\nCustomers after update:");
            printAllCustomers(collection);

            // DELETE (Remove Jason)
            collection.deleteOne(new Document("name", "Jason Mathew"));

            System.out.println("\nCustomers after deletion:");
            printAllCustomers(collection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Print all customers with first name, last name email, product, and price
     *
     * @param collection MongoCollection<Document> to read from
     */
    private static void printAllCustomers(MongoCollection<Document> collection) {
        FindIterable<Document> customers = collection.find();
        for (Document c : customers) {
            System.out.println("First Name: " + c.getString("firstName"));
            System.out.println("Last Name: " + c.getString("lastName"));
            System.out.println("Email: " + c.getString("email"));
            System.out.println("Product: " + c.getString("product"));
            System.out.println("Price: $" + c.getString("price"));
            System.out.println("-----------------");
        }
    }
}
