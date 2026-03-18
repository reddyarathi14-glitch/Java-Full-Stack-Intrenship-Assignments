package mongodb;


import com.mongodb.client.*;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class LibraryMongoDB {

    public static void main(String[] args) {

        MongoClient client = MongoClients.create("mongodb+srv://ArathiA:Arathi123@cluster0.swgkpcx.mongodb.net/?appName=Cluster0");

        MongoDatabase database = client.getDatabase("libraryDB");
        MongoCollection<Document> collection = database.getCollection("books");

        System.out.println("Connected to MongoDB");

        // READ ALL BOOKS
        System.out.println("\nAll Books:");
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }

        // READ BY GENRE
        System.out.println("\nComputer Science Books:");
        for (Document doc : collection.find(eq("genre","Computer Science"))) {
            System.out.println(doc.toJson());
        }

        // UPDATE BOOK
        collection.updateOne(eq("book_id",101), set("status","Issued"));
        System.out.println("\nBook 101 status updated");

        // DELETE BOOK
        collection.deleteOne(eq("book_id",103));
        System.out.println("Book 103 deleted");

        client.close();
    }
}