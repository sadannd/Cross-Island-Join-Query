package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//1.5 sec
public class ShowMySqlData{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        long startTime = System.currentTimeMillis(); // Record start time

		String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "password";

        //String url = "jdbc:postgresql://remote-server-ip:5432/your_database";
        //String user = "postgres";
        //String password = "your_password";
        try {
            // Register the PostgreSQL JDBC driver
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
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
               
                System.out.println();
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
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
                //break;
            }
            
            
            System.out.println("\n****Transaction Data****\n");
            sqlQuery = "SELECT * FROM transactions ";
                    
            // Execute a simple SELECT query
            resultSet = statement.executeQuery(sqlQuery);
            metaData = resultSet.getMetaData();
            columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
                //break;
            }
            
            
            
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
            long endTime = System.currentTimeMillis(); // Record end time
            long executionTime = endTime - startTime; // Calculate execution time
            System.out.println("Execution time: " + executionTime + " milliseconds");
	}
     catch(Exception e)
     {
        	System.out.println("error");
     }
		
	}



}
