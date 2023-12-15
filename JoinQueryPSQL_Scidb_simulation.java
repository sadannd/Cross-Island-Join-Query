package bigdawg.bigdawg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
public class JoinQueryPSQL_Scidb_simulation {
    public static void main(String[] args) {
        int n = 1; // Number of threads to create

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

        System.out.println("All threads have started and completed.");
    }
}

class Worker implements Runnable {
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
            long startTime = System.currentTimeMillis();

        	String tName=Thread.currentThread().getName();
            startSignal.await(); // Wait for the signal to start
            // Your thread's work goes here
            String curlCommand = "curl -X POST -d \"bdarray(project(filter(TABLE_NAME,i<10),CLOUMN_NAMES_LIST));\""+" http:/IP_ADDRESS:8080/bigdawg/query/>scidb"+tName+".txt";

            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", curlCommand);
            
            //ProcessBuilder processBuil der = new ProcessBuilder("/bin/bash", "-c", curlCommand);
            processBuilder.redirectErrorStream(true);
            try {
    			Process process = processBuilder.start();
    			process.waitFor();
    		} catch (IOException | InterruptedException e) {
    			e.printStackTrace();
    		}
            
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("scidb"+tName+".txt"))) {
                // Read lines and add them to the list
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            String line;
            String[] parts = lines.get(0).split("\\s+");

           // System.out.println();
            curlCommand = "curl -X POST -d \"bdrel(select * from TABLE_NAME;)\""+" http:/IP_ADDRESS:8080/bigdawg/query/>attribute"+tName+".txt";
            processBuilder = new ProcessBuilder("cmd.exe", "/c", curlCommand);

            //ProcessBuilder processBuil der = new ProcessBuilder("/bin/bash", "-c", curlCommand);
            processBuilder.redirectErrorStream(true);
            try {
    			Process process = processBuilder.start();
    			process.waitFor();
    	        
    		} catch (IOException | InterruptedException e) {
    			e.printStackTrace();
    		}
            
            HashMap<String, String> attributeName_desc = new HashMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader("attribute"+tName+".txt"))) {
                while ((line = br.readLine()) != null) {
                    String[] parts_attributes = line.split("\t");
                    attributeName_desc.put(parts_attributes[2].trim(), parts_attributes[4].trim());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("attributeName   |   Description\n");
			
			  for(int i=0;i<parts.length;i++) {
			  if(attributeName_desc.containsKey(parts[i])) {
			  System.out.println(parts[i]+"\t-\t"+attributeName_desc.get(parts[i])); } else
			  { System.out.println(parts[i]+"\t-\t"+"Description does not exists");
			  
			  } }
			 
            System.out.println();
			
			  for(int i=0;i<lines.size();i++) { System.out.println(lines.get(i)); }
			 
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println(Thread.currentThread().getName()+" Thread execution time: " + executionTime + " milliseconds");
    	
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            doneSignal.countDown(); // Signal that this thread has completed
        }
    }
}

