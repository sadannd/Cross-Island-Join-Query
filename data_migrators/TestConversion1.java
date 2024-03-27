package mypackage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TestConversion1 {

    public static void main(String[] args) {
        // Database connection parameters
    	String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "password";

        // JSON file paths
        String customerFilePath = "/home/sada/Downloads/customers.json";
        String accountFilePath = "/home/sada/Downloads/accounts.json";
        String transactionFilePath = "/home/sada/Downloads/transactions.json";

        // Load PostgreSQL JDBC driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Establish database connection
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // Process each JSON file
            //processCustomerJson(conn, customerFilePath);
            //processAccountJson(conn, accountFilePath);
            processTransactionJson(conn, transactionFilePath);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void processCustomerJson(Connection conn, String filePath) throws SQLException {
        try {
            // Read JSON file
            ObjectMapper mapper = new ObjectMapper();
            JsonNode customersNode = mapper.readTree(new File(filePath));

            // Iterate over each customer entry
            for (JsonNode customerNode : customersNode) {
                // Extract data from customer JSON object
                String _id = customerNode.has("_id") ? customerNode.get("_id").get("$oid").asText() : null;
                String username = customerNode.has("username") ? customerNode.get("username").asText() : null;
                String name = customerNode.has("name") ? customerNode.get("name").asText() : null;
                String address = customerNode.has("address") ? customerNode.get("address").asText() : null;
                long birthdate = customerNode.has("birthdate") ? customerNode.get("birthdate").get("$date").get("$numberLong").asLong() : 0;
                String email = customerNode.has("email") ? customerNode.get("email").asText() : null;
                boolean active = customerNode.has("active") && customerNode.get("active").isBoolean() ? customerNode.get("active").asBoolean() : false;

                // Insert into customers table
                String insertCustomerQuery = "INSERT INTO customers (_id, username, name, address, birthdate, email, active) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertCustomerQuery)) {
                    stmt.setString(1, _id);
                    stmt.setString(2, username);
                    stmt.setString(3, name);
                    stmt.setString(4, address);
                    stmt.setTimestamp(5, new java.sql.Timestamp(birthdate));
                    stmt.setString(6, email);
                    stmt.setBoolean(7, active);
                    stmt.executeUpdate();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void processAccountJson(Connection conn, String filePath) throws SQLException {
        // Similar process as processCustomerJson for account data
    }

    private static void processTransactionJson(Connection conn, String filePath) throws SQLException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode transactionsNode = objectMapper.readTree(new File(filePath));

            for (JsonNode transactionNode : transactionsNode) {
                String accountId = transactionNode.has("account_id") ? transactionNode.get("account_id").get("$numberInt").asText() : null;
                int transactionCount = transactionNode.has("transaction_count") ? transactionNode.get("transaction_count").get("$numberInt").asInt() : 0;
                JsonNode transactionsArrayNode = transactionNode.get("transactions");
                System.out.println("here");
                if (transactionsArrayNode != null && transactionsArrayNode.isArray()) {
                	System.out.println("here111");
                    for (JsonNode singleTransaction : transactionsArrayNode) {
                        Timestamp date = singleTransaction.has("date") ? new Timestamp(singleTransaction.get("date").get("$date").get("$numberLong").asLong()) : null;
                        int amount = singleTransaction.has("amount") ? singleTransaction.get("amount").get("$numberInt").asInt() : 0;
                        String transactionCode = singleTransaction.has("transaction_code") ? singleTransaction.get("transaction_code").asText() : null;
                        String symbol = singleTransaction.has("symbol") ? singleTransaction.get("symbol").asText() : null;
                        double price = singleTransaction.has("price") ? singleTransaction.get("price").asDouble() : 0.0;
                        double total = singleTransaction.has("total") ? singleTransaction.get("total").asDouble() : 0.0;

                        // Insert transaction data into the database
                        String insertQuery = "INSERT INTO transactions (account_id, transaction_count, date, amount, transaction_code, symbol, price, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
                            preparedStatement.setString(1, accountId);
                            preparedStatement.setInt(2, transactionCount);
                            preparedStatement.setTimestamp(3, date);
                            preparedStatement.setInt(4, amount);
                            preparedStatement.setString(5, transactionCode);
                            preparedStatement.setString(6, symbol);
                            preparedStatement.setDouble(7, price);
                            preparedStatement.setDouble(8, total);
                            preparedStatement.executeUpdate();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
