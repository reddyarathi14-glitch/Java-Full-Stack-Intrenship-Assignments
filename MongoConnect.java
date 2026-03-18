package mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnect {

    public static void main(String[] args) {

        String uri = "mongodb+srv://student:pass123@cluster0.abcd123.mongodb.net/studentDB?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("studentDB");

            System.out.println("Connected to MongoDB Atlas Cluster successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}