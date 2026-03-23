package mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

public class StudentUpdateDelete {
    public static void main(String[] args) {

        // Connect to MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb+srv://ArathiA:Arathi123@cluster0.swgkpcx.mongodb.net/?appName=Cluster0");

        // Select Database
        MongoDatabase database = mongoClient.getDatabase("studentSkillDB");

        // Select Collection
        MongoCollection<Document> collection = database.getCollection("students");

        // =========================
        // 🔄 UPDATE OPERATIONS
        // =========================

        // 1. Add new skill (MongoDB)
        collection.updateOne(
                new Document("studentName", "Priya"),
                new Document("$push", new Document("skills", "MongoDB"))
        );
        System.out.println("Skill added successfully!");

        // 2. Update level (Intermediate → Advanced)
        collection.updateOne(
                new Document("studentName", "Priya"),
                new Document("$set", new Document("level", "Advanced"))
        );
        System.out.println("Level updated!");

        // 3. Add new practice record
        Document newPractice = new Document("date", "2026-03-24")
                .append("hours", 4);

        collection.updateOne(
                new Document("studentName", "Priya"),
                new Document("$push", new Document("practiceHistory", newPractice))
        );
        System.out.println("Practice record added!");

        // =========================
        // ❌ DELETE OPERATIONS
        // =========================

        // 1. Delete specific skill (remove "Java")
        collection.updateOne(
                new Document("studentName", "Priya"),
                new Document("$pull", new Document("skills", "Java"))
        );
        System.out.println("Skill removed!");

        // 2. Delete specific practice record (hours = 2)
        collection.updateOne(
                new Document("studentName", "Priya"),
                new Document("$pull",
                        new Document("practiceHistory",
                                new Document("hours", 2)))
        );
        System.out.println("Practice record deleted!");

        // 3. Delete entire student document
        collection.deleteOne(new Document("studentName", "Priya"));
        System.out.println("Student deleted!");

        // Close connection
        mongoClient.close();
    }
}
