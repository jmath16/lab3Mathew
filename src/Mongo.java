/** Project: Lab 3 Database Assignment
 * Purpose Details: Perform CRUD Create. Read, Update and Delete
 * Course: IST 242
 * Author: Jason Mathew
 * Date Developed: 2/28/26
 * Last Date Changed: 3/05/26
 * Rev:

 */

import com.mongodb.client.*;
import org.bson.Document;

public class Mongo {

    private final MongoClient mongoClient;
    private final MongoCollection<Document> collection;

    public Mongo() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("cafe");
        collection = database.getCollection("customers");
    }

    public void close() {
        mongoClient.close();
    }

    // CREATE
    public void create(Customer customer) {

        Document doc = new Document("id", customer.getId())
                .append("first_name", customer.getFirstName())
                .append("last_name", customer.getLastName())
                .append("email", customer.getEmail())
                .append("product", customer.getProduct())
                .append("quantity", customer.getQuantity())
                .append("price", customer.getPrice());

        collection.insertOne(doc);
    }

    // READ
    public void read(int id) {

        Document doc = collection.find(new Document("id", id)).first();

        if (doc == null) {
            System.out.println("Mongo: No customer found with id = " + id);
        } else {
            System.out.println(doc.toJson());
        }
    }

    // UPDATE
    public void update(Customer customer) {

        Document filter = new Document("id", customer.getId());

        Document set = new Document("first_name", customer.getFirstName())
                .append("last_name", customer.getLastName())
                .append("email", customer.getEmail())
                .append("product", customer.getProduct())
                .append("quantity", customer.getQuantity())
                .append("price", customer.getPrice());

        collection.updateOne(filter, new Document("$set", set));
    }

    // DELETE
    public void delete(int id) {

        collection.deleteOne(new Document("id", id));
    }
}
