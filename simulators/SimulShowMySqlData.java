package mypackage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;
public class SimulShowMySqlData {
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
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println(Thread.currentThread().getName()+" Thread execution time: " + executionTime + " milliseconds");
            System.out.println(Thread.currentThread().getName()+" All Thread completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads have started and completed.");
    }
}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
    public void run() {
        try {
        	System.out.println("Thread start");
        	startSignal.await();
            long startTime = System.currentTimeMillis();
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "password";
             // Wait for the signal to start
            // Your thread's work goes here
            System.out.println("Thread started.");
            
            Class.forName("org.postgresql.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(url, user, password);

            // Create a statement
            Statement statement = connection.createStatement();

            String sqlQuery = "SELECT * FROM accounts1 " +
                    "JOIN users1 ON accounts1.customer_id = users1.customer_id " +
                    "JOIN tier_details1 ON accounts1.customer_id = tier_details1.customer_id ";
            // Execute a simple SELECT query
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            System.out.println("****Customers Data****\n");

            for (int i = 1; i <= columnCount; i++) {
                //System.out.print(metaData.getColumnName(i) + "\t");
            }
            //System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    //System.out.print(resultSet.getString(i) + "\t");
                }
               
                //System.out.println();
                //break;
            }
            
            
            System.out.println("\n****Accounts Data****\n");
            
            sqlQuery = "SELECT * FROM accounts " +
                    "JOIN account_products ON accounts.account_id = account_products.account_id " +
                    "JOIN products ON account_products.product_id = products.product_id";
            // Execute a simple SELECT query
            resultSet = statement.executeQuery(sqlQuery);
            metaData = resultSet.getMetaData();
            columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                //System.out.print(metaData.getColumnName(i) + "\t");
            }
            //System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    //System.out.print(resultSet.getString(i) + "\t");
                }
                //System.out.println();
                //break;
            }
            
            
            System.out.println("\n****Transaction Data****\n");
            sqlQuery = "SELECT * FROM transactions ";
                    
            // Execute a simple SELECT query
            resultSet = statement.executeQuery(sqlQuery);
            metaData = resultSet.getMetaData();
            columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                //System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    //System.out.print(resultSet.getString(i) + "\t");
                }
                //System.out.println();
                //break;
            }
            
            
            
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();

            
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            System.out.println(Thread.currentThread().getName()+" Thread execution time: " + executionTime + " milliseconds");
            System.out.println(Thread.currentThread().getName()+" Thread completed.");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doneSignal.countDown(); // Signal that this thread has completed
        }
    }
}

