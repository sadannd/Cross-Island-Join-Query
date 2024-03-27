package mypackage;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
//0.874 s
public class ShowMongoData {

    public static void main(String[] args) {
    	 long startTime = System.currentTimeMillis(); // Record start time
        // Connect to MongoDB server
    	MongoClient mongoClient = new MongoClient( "localhost" , 2717 );
		MongoCursor<String> dbsCursor = mongoClient.listDatabaseNames().iterator();
		List<String> colelctionsList=new ArrayList<String>();
		colelctionsList.add("customers");
		colelctionsList.add("testCollection");
		colelctionsList.add("Transaction");
		MongoDatabase db = mongoClient.getDatabase("Test");
		int itr=0;
		for(int i=0;i<colelctionsList.size();i++)
		{
			
			   System.out.println("\n***"+colelctionsList.get(i)+"***\n");
			   MongoCollection<org.bson.Document>  collection= db.getCollection(colelctionsList.get(i));
			  

			   MongoCursor<Document> cursor = collection.find().iterator();
			   while (cursor.hasNext()) {
			       System.out.println(colelctionsList.get(i)+": "+cursor.next());
			       itr++;
			   }
		}		
		
		long endTime = System.currentTimeMillis(); // Record end time
        long executionTime = endTime - startTime; // Calculate execution time
        System.out.println("Execution time: " + executionTime + " milliseconds");
        System.out.println(itr);
			
    }
}
