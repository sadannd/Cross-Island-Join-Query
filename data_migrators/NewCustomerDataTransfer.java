package mypackage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.io.FileReader;
import java.sql.*;

public class NewCustomerDataTransfer {
    public static void main(String[] args) {
        try {
            // Connect to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password");

            // Read JSON file
            FileReader reader = new FileReader("/home/sada/Downloads/customers.json");

            // Parse JSON data
            Gson gson = new Gson();
            JsonObject jsonData = gson.fromJson(reader, JsonObject.class);

            // Extract user data
            JsonObject user = jsonData.getAsJsonObject();
            String customerId = user.getAsJsonObject("_id").get("$oid").getAsString();
            String username = user.get("username").getAsString();
            String name = user.get("name").getAsString();
            String address = user.get("address").getAsString();
            String birthday=user.get("birthday").getAsString();
            String email = user.get("email").getAsString();
            boolean active = user.get("active").getAsBoolean();

            // Insert user data into users table
            PreparedStatement userStatement = connection.prepareStatement("INSERT INTO users1 (customer_id, username, name, address,birthday, email, active) VALUES (?, ?, ?, ?, ?, ?, ?)");
            userStatement.setString(1, customerId);
            userStatement.setString(2, username);
            userStatement.setString(3, name);
            userStatement.setString(4, address);
            userStatement.setString(4, birthday);
            userStatement.setString(5, email);
            userStatement.setBoolean(6, active);
            userStatement.executeUpdate();

            // Extract account data
            JsonArray accounts = user.getAsJsonArray("accounts");
            for (int i = 0; i < accounts.size(); i++) {
                String accountNumber = accounts.get(i).getAsString();
                // Insert account data into accounts table
                PreparedStatement accountStatement = connection.prepareStatement("INSERT INTO accounts1 (customer_id, account_number) VALUES (?, ?)");
                accountStatement.setString(1, customerId);
                accountStatement.setString(2, accountNumber);
                accountStatement.executeUpdate();
            }

            // Extract tier details
            JsonObject tierDetails = user.getAsJsonObject("tier_and_details");
            for (String key : tierDetails.keySet()) {
                JsonObject tier = tierDetails.getAsJsonObject(key);
                String tierId = tier.get("id").getAsString();
                String tierName = tier.get("tier").getAsString();
                boolean tierActive = tier.get("active").getAsBoolean();
                String benefits = tier.getAsJsonArray("benefits").toString();
                // Insert tier details into tier_details table
                PreparedStatement tierStatement = connection.prepareStatement("INSERT INTO tier_details1 (customer_id, tier_id, tier, active, benefits) VALUES (?, ?, ?, ?, ?)");
                tierStatement.setString(1, customerId);
                tierStatement.setString(2, tierId);
                tierStatement.setString(3, tierName);
                tierStatement.setBoolean(4, tierActive);
                tierStatement.setString(5, benefits);
                tierStatement.executeUpdate();
            }

            // Close resources
            reader.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

