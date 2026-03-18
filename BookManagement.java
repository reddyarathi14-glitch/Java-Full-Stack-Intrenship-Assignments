package mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class BookManagement {

    public static void main(String[] args) {

        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase db = mongoClient.getDatabase("LibraryDB");

            MongoCollection<Document> collection = db.getCollection("books");

            // Insert Books
            Document book1 = new Document("book_id",101)
                    .append("title","Data Structures")
                    .append("author","Mark Allen")
                    .append("genre","Computer Science")
                    .append("status","Available");

            Document book2 = new Document("book_id",102)
                    .append("title","Database Systems")
                    .append("author","Henry Korth")
                    .append("genre","Computer Science")
                    .append("status","Available");

            Document book3 = new Document("book_id",103)
                    .append("title","English Grammar")
                    .append("author","Wren Martin")
                    .append("genre","Education")
                    .append("status","Available");

            collection.insertOne(book1);
            collection.insertOne(book2);
            collection.insertOne(book3);

            System.out.println("Books Inserted");

            // Read all books
            System.out.println("All Books:");
            for(Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }

            // Read specific genre
            System.out.println("Computer Science Books:");
            for(Document doc : collection.find(new Document("genre","Computer Science"))) {
                System.out.println(doc.toJson());
            }

            // Update book status
            collection.updateOne(
                    new Document("book_id",101),
                    new Document("$set", new Document("status","Issued"))
            );

            System.out.println("Book Updated");

            // Delete book
            collection.deleteOne(new Document("book_id",103));

            System.out.println("Book Deleted");

        }
    }
}