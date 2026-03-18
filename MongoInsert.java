package mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

public class MongoInsert {

    public static void main(String[] args) {

        // Replace with your MongoDB Atlas username and password
        String uri = "mongodb+srv://ArathiA:Arathi123@cluster0.swgkpcx.mongodb.net/?appName=Cluster0";
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // Connect Database
            MongoDatabase database = mongoClient.getDatabase("libraryDB");

            // Connect Collection
            MongoCollection<Document> collection = database.getCollection("books");

            // Create Document
            Document doc = new Document("book_id", 1)
                    .append("title", "Java Programming")
                    .append("author", "Herbert Schildt")
                    .append("status", "Available");

            // Insert Document
            collection.insertOne(doc);

            System.out.println("Data inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
