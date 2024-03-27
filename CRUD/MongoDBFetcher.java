package mypackage;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBFetcher implements DatabaseFetcher{

	@Override
	public List<String[]> fetchData(String query) {
		// TODO Auto-generated method stub
		List<String[]> list=new ArrayList<String[]>();
		MongoClient mongoClient = new MongoClient( "localhost" , 2717 );
		MongoCursor<String> dbsCursor = mongoClient.listDatabaseNames().iterator();
		   while(dbsCursor.hasNext()) {
		          System.out.println(dbsCursor.next());
		      }
		
			MongoDatabase db = mongoClient.getDatabase("Test");

		   MongoCollection<org.bson.Document>  collection= db.getCollection("customers");
		   System.out.println("collection created ");

		 //Inserting sample records by creating documents.
		   /*Document doc =new Document("name","SimplifyingTech");
		   doc.append("id",101);  
		   doc.append("Subscribers",100);  
		   doc.append("Genre", "Programming");
		   collection.insertOne(doc);
		   System.out.println("Insert is completed");
		    
		   Document doc2 =new Document("name","CarryMinati");
		   doc2.append("id",102);  
		   doc2.append("Subscribers",24000000);  
		   doc2.append("Genre", "Comedy");
		   collection.insertOne(doc2);
		   System.out.println("Insert is completed");
		   
		 //Listing All Mongo Documents in Collection
		   FindIterable<Document> iterDoc = collection.find();
		   int i = 1;
		   // Getting the iterator
		   System.out.println("Listing All Mongo Documents");
		   Iterator it = iterDoc.iterator();
		   while (it.hasNext()) {
		       System.out.println(it.next());
		       i++;
		   }*/
		   //specific document retrieving in a collection
		   BasicDBObject searchQuery = new BasicDBObject();
		   searchQuery.put("name", "SimplifyingTech");
		   System.out.println("Retrieving specific Mongo Document");
		   MongoCursor<Document> cursor = collection.find().iterator();
		   while (cursor.hasNext()) {
			   
		       System.out.println(cursor.next());
		       list.add(new String[] {"Asdsa"});
		   }
		return list;
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
