package mypackage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
public class SimulShowMongoData {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int n = 16; // Number of threads to create

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(n);

        for (int i = 1; i <= n; i++) {
            Thread thread = new Thread(new Worker(startSignal, doneSignal));
            thread.setName(String.valueOf(i));
            thread.start(); // Start each thread
        }

        // Release the threads to start simultaneously
        startSignal.countDown();

        try {
            // Wait for all threads to finish
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println(Thread.currentThread().getName()+" Thread execution time: " + executionTime + " milliseconds");
        System.out.println(Thread.currentThread().getName()+" All Thread completed.");
        System.out.println("All threads have started and completed.");
    }
}

class Worker1 implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    int counter=0;
    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
        	startSignal.await();
            long startTime = System.currentTimeMillis();

             // Wait for the signal to start
            // Your thread's work goes here
            System.out.println("Thread started.");
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
    			       cursor.next();
    				   itr++;
    			   }
    		}		
    		
    		long endTime = System.currentTimeMillis(); // Record end time
            long executionTime = endTime - startTime; // Calculate execution time
            System.out.println("Execution time: " + executionTime + " milliseconds");
            System.out.println(itr);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            doneSignal.countDown(); // Signal that this thread has completed
        }
    }
}

