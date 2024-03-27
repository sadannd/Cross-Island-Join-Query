package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoPsqlJoin_updated {
    static Map<Integer,String> map=new HashMap<Integer,String>();

    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 2717);

            MongoDatabase database = mongoClient.getDatabase("Test");
            MongoCollection<Document> collection = database.getCollection("customers");
            
            FindIterable<Document> documents = collection.find();
            int num=0;
            //setProducts(map);
            Connection connection=DatabaseConnection_Hikari.getConnection();
            for (Document doc : documents) {
            	System.out.println("Count is:-"+num++);
            
                System.out.println("Document ID: " + doc.getObjectId("_id"));
                System.out.println("Account Names:");
                String customerId=doc.getObjectId("_id").toString();
                String username = doc.getString("username");
                String name = doc.getString("name");
                String address = doc.getString("address");
                String birthdate = doc.getDate("birthdate").toString();
                String email = doc.getString("email");
                String active = doc.containsKey("active") ? doc.getBoolean("active").toString() : "N/A";

                System.out.println("Username: " + username);
                System.out.println("Name: " + name);
                System.out.println("Address: " + address);
                System.out.println("Birthdate: " + birthdate);
                System.out.println("Email: " + email);
                System.out.println("Active: " + active);
                
                PreparedStatement userStatement = connection.prepareStatement("INSERT INTO users1 (customer_id, username, name, address,birthdate, email, active) VALUES (?, ?, ?, ?, ?, ?, ?)");
                userStatement.setString(1, customerId);
                userStatement.setString(2, username);
                userStatement.setString(3, name);
                userStatement.setString(4, address);
                userStatement.setString(5, birthdate);
                userStatement.setString(6, email);
                userStatement.setString(7, active);
                userStatement.executeUpdate();
                // Extract and print account names
                List<Integer> accounts = (List<Integer>) doc.get("accounts");
                if (accounts != null) {
                    for (Integer account : accounts) {
                        System.out.println("- " + account);
                        
                        PreparedStatement accountStatement = connection.prepareStatement("INSERT INTO accounts1 (customer_id, account_number) VALUES (?, ?)");
                        accountStatement.setString(1, customerId);
                        accountStatement.setString(2, account.toString());
                        accountStatement.executeUpdate();
                        
						/*
						 * String sql = "SELECT * FROM transactions WHERE account_id = ?"; //Connection
						 * connection=getConnection();
						 * 
						 * 
						 * PreparedStatement statement = connection.prepareStatement(sql);
						 * 
						 * statement.setString(1, account.toString());
						 * 
						 * // Execute the query ResultSet resultSet = statement.executeQuery();
						 * iterateTransactions(resultSet);
						 * 
						 * 
						 * String sql_accounts = "SELECT * FROM accounts WHERE account_id = ?";
						 * statement = connection.prepareStatement(sql_accounts);
						 * 
						 * statement.setInt(1, account);
						 * 
						 * // Execute the query resultSet = statement.executeQuery();
						 * iterateAccounts(resultSet);
						 * 
						 * String sql_accounts_products =
						 * "SELECT * FROM account_products WHERE account_id = ?"; statement =
						 * connection.prepareStatement(sql_accounts_products);
						 * 
						 * statement.setInt(1, account);
						 * 
						 * // Execute the query resultSet = statement.executeQuery();
						 * iterateAccounts_Products(resultSet);
						 */
                        
                        
                                                
                    }
                }


                System.out.println("Tier and Details:");
                // Extract and print tier and details
                Document tierAndDetails = doc.get("tier_and_details", Document.class);
                for (String key : tierAndDetails.keySet()) {
					
					  Document tier = tierAndDetails.get(key, Document.class);
					  System.out.println("Tier ID: " + key); 
					  System.out.println("Tier Name: " + tier.getString("tier")); 
					  System.out.println("Active: " + tier.getBoolean("active")); System.out.println("Benefits:"); List<String>
					  benefits = (List<String>) tier.get("benefits"); 
					  StringBuilder sb=new StringBuilder();

					  if (benefits != null) { 
						  for(String benefit : benefits) 
						  { 
							  sb.append(benefit+",");
							  System.out.println("- " + benefit); } 
						  }
					 
                	
                	PreparedStatement tierStatement = connection.prepareStatement("INSERT INTO tier_details1 (customer_id, tier_id, tier, active, benefits) VALUES (?, ?, ?, ?, ?)");
                    tierStatement.setString(1, customerId);
                    tierStatement.setString(2, key);
                    tierStatement.setString(3, tier.getString("tier"));
                    tierStatement.setBoolean(4, tier.getBoolean("active"));
                    tierStatement.setString(5, sb.toString());
                    tierStatement.executeUpdate();
                }
                System.out.println("-------------");
                //break;
            }
            System.out.println("Done");
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void iterateAccounts_Products(ResultSet resultSet) throws SQLException {
    	while (resultSet.next()) {
            int account_id = resultSet.getInt("account_id");
            int product_id = resultSet.getInt("product_id");
                        
            System.out.println("account_id: " + account_id + ", product_names: " + map.get(product_id) );
	}
    }
	private static void setProducts(Map<Integer, String> map) throws SQLException {
    	String sql = "SELECT * FROM products";
    	Connection connection=getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
                
        // Execute the query
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int product_id = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            map.put(product_id, name);    
	}
    	connection.close();
		
	}
	private static void iterateAccounts(ResultSet resultSet) throws SQLException {
    	while (resultSet.next()) {
            int account_id = resultSet.getInt("account_id");
            int limit_value = resultSet.getInt("limit_value");
                        
            System.out.println("account_id: " + account_id + ", limit_value: " + limit_value );
            break;
                              
	}
		
	}
	private static void iterateTransactions(ResultSet resultSet) throws SQLException {
    	
    	while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int account_id = resultSet.getInt("account_id");
            int transaction_count = resultSet.getInt("transaction_count");
            String date = resultSet.getString("date");
            double amount = resultSet.getDouble("amount");
            String transaction_code = resultSet.getString("transaction_code");
            String symbol = resultSet.getString("symbol");
            double price = resultSet.getDouble("price");
            double total = resultSet.getDouble("total");
            
            // Print or process the retrieved data
            System.out.println("id: " + id + ", account_id: " + account_id + ", transaction_count: " + transaction_count +
                               ", date: " + date + ", amount: " + amount + ", transaction_code: " + transaction_code +
                               ", symbol: " + symbol + ", price: " + price + ", total: " + total);
        }		
	}
	public static Connection getConnection() throws SQLException {
        
        try {
    		String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "password";
            // Register the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(url, user, password);            
            // Connect to the database
            
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            // Handle any errors
            throw new SQLException("Failed to connect to the database: " + e.getMessage());
        }
    }
	
	
}
