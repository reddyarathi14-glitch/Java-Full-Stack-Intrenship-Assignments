package mongodb;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;

public class FoodDeliverySystem {

    public static void main(String[] args) {

        // 🔹 Connection
        String uri = "mongodb+srv://ArathiA:Arathi123@cluster0.swgkpcx.mongodb.net/?appName=Cluster0"; // change if using Atlas
        MongoClient client = MongoClients.create(uri);

        MongoDatabase db = client.getDatabase("foodDB");
        MongoCollection<Document> orders = db.getCollection("orders");

        // 🔹 Insert Data
        orders.insertMany(Arrays.asList(
                new Document("order_id", 101).append("customer_name", "Rahul").append("restaurant", "Pizza Hut")
                        .append("food_item", "Pizza").append("quantity", 2).append("price", 500)
                        .append("city", "Bangalore").append("status", "Delivered"),

                new Document("order_id", 102).append("customer_name", "Anu").append("restaurant", "KFC")
                        .append("food_item", "Burger").append("quantity", 1).append("price", 200)
                        .append("city", "Chennai").append("status", "Pending"),

                new Document("order_id", 103).append("customer_name", "Ravi").append("restaurant", "Dominos")
                        .append("food_item", "Pizza").append("quantity", 3).append("price", 900)
                        .append("city", "Bangalore").append("status", "Delivered"),

                new Document("order_id", 104).append("customer_name", "Priya").append("restaurant", "Subway")
                        .append("food_item", "Sandwich").append("quantity", 2).append("price", 300)
                        .append("city", "Hyderabad").append("status", "Pending"),

                new Document("order_id", 105).append("customer_name", "Kiran").append("restaurant", "Pizza Hut")
                        .append("food_item", "Pasta").append("quantity", 1).append("price", 350)
                        .append("city", "Chennai").append("status", "Delivered")
        ));

        System.out.println("✅ Data Inserted\n");

        // 🔹 Read All
        System.out.println("📌 All Orders:");
        for (Document doc : orders.find()) {
            System.out.println(doc.toJson());
        }

        // 🔹 Filter by City
        System.out.println("\n📌 Orders from Bangalore:");
        for (Document doc : orders.find(eq("city", "Bangalore"))) {
            System.out.println(doc.toJson());
        }

        // 🔹 Filter by Status
        System.out.println("\n📌 Delivered Orders:");
        for (Document doc : orders.find(eq("status", "Delivered"))) {
            System.out.println(doc.toJson());
        }

        // 🔹 Aggregation - Total Revenue by City
        System.out.println("\n📊 Total Revenue by City:");
        orders.aggregate(Arrays.asList(
                group("$city", sum("totalRevenue", "$price"))
        )).forEach(doc -> System.out.println(doc.toJson()));

        // 🔹 Most Ordered Food Item
        System.out.println("\n📊 Most Ordered Food Item:");
        orders.aggregate(Arrays.asList(
                group("$food_item", sum("count", 1)),
                sort(descending("count")),
                limit(1)
        )).forEach(doc -> System.out.println(doc.toJson()));

        // 🔹 Average Price per Restaurant
        System.out.println("\n📊 Average Order per Restaurant:");
        orders.aggregate(Arrays.asList(
                group("$restaurant", avg("avgPrice", "$price"))
        )).forEach(doc -> System.out.println(doc.toJson()));

        // 🔹 Count by Status
        System.out.println("\n📊 Order Count by Status:");
        orders.aggregate(Arrays.asList(
                group("$status", sum("count", 1))
        )).forEach(doc -> System.out.println(doc.toJson()));

        // 🔹 Indexing
        orders.createIndex(new Document("customer_name", 1));
        orders.createIndex(new Document("city", 1));
        System.out.println("\n✅ Indexes Created");

        // 🔹 Advanced Query
        System.out.println("\n📌 Orders with price > 300:");
        for (Document doc : orders.find(gt("price", 300)).sort(descending("price"))) {
            System.out.println(doc.toJson());
        }

        // 🔹 Close Connection
        client.close();
        System.out.println("\n✅ Connection Closed");
    }
}
