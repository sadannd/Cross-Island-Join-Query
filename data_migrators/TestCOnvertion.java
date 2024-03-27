package mypackage;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import org.json.*;

public class TestCOnvertion {
    public static void main(String[] args) throws IOException {
        //String json_data = "[{\"_id\":{\"$oid\":\"5ca4bbc7a2dd94ee5816238c\"},\"account_id\":{\"$numberInt\":\"371138\"},\"limit\":{\"$numberInt\":\"9000\"},\"products\":[\"Derivatives\",\"InvestmentStock\"]},{\"_id\":{\"$oid\":\"5ca4bbc7a2dd94ee5816238d\"},\"account_id\":{\"$numberInt\":\"557378\"},\"limit\":{\"$numberInt\":\"10000\"},\"products\":[\"InvestmentStock\",\"Commodity\",\"Brokerage\",\"CurrencyService\"]},{\"_id\":{\"$oid\":\"5ca4bbc7a2dd94ee5816238e\"},\"account_id\":{\"$numberInt\":\"198100\"},\"limit\":{\"$numberInt\":\"10000\"},\"products\":[\"Derivatives\",\"CurrencyService\",\"InvestmentStock\"]},{\"_id\":{\"$oid\":\"5ca4bbc7a2dd94ee5816238f\"},\"account_id\":{\"$numberInt\":\"674364\"},\"limit\":{\"$numberInt\":\"10000\"},\"products\":[\"InvestmentStock\"]},{\"_id\":{\"$oid\":\"5ca4bbc7a2dd94ee58162390\"},\"account_id\":{\"$numberInt\":\"278603\"},\"limit\":{\"$numberInt\":\"10000\"},\"products\":[\"Commodity\",\"InvestmentStock\"]},{\"_id\":{\"$oid\":\"5ca4bbc7a2dd94ee58162391\"},\"account_id\":{\"$numberInt\":\"383777\"},\"limit\":{\"$numberInt\":\"10000\"},\"products\":[\"CurrencyService\",\"Derivatives\",\"InvestmentFund\",\"Commodity\",\"InvestmentStock\"]},{\"_id\":{\"$oid\":\"5ca4bbc7a2dd94ee58162392\"},\"account_id\":{\"$numberInt\":\"794875\"},\"limit\":{\"$numberInt\":\"9000\"},\"products\":[\"InvestmentFund\",\"InvestmentStock\"]},{\"_id\":{\"$oid\":\"5ca4bbc7a2dd94ee58162393\"},\"account_id\":{\"$numberInt\":\"328304\"},\"limit\":{\"$numberInt\":\"10000\"},\"products\":[\"Derivatives\",\"InvestmentStock\",\"CurrencyService\"]},{\"_id\":{\"$oid\":\"5ca4bbc7a2dd94ee58162394\"},\"account_id\":{\"$numberInt\":\"487188\"},\"limit\":{\"$numberInt\":\"10000\"},\"products\":[\"Brokerage\",\"CurrencyService\",\"InvestmentStock\"]},{\"_id\":{\"$oid\":\"5ca4bbc7a2dd94ee58162395\"},\"account_id\":{\"$numberInt\":\"910579\"},\"limit\":{\"$numberInt\":\"10000\"},\"products\":[\"Brokerage\",\"InvestmentStock\"]},{\"_id\":{\"$oid\":\"5ca4bbc7a2dd94ee58162396\"},\"account_id\":{\"$numberInt\":\"260499\"},\"limit\":{\"$numberInt\":\"10000\"},\"products\":[\"InvestmentFund\",\"Derivatives\",\"InvestmentStock\"]}]";
        String json_data1 = new String(Files.readAllBytes(Paths.get("/home/sada/Downloads/accounts.json")));
        FileReader reader = new FileReader("/home/sada/Downloads/accounts.json");
        StringBuilder stringBuilder = new StringBuilder();
        int character;
        while ((character = reader.read()) != -1) {
            stringBuilder.append((char) character);
        }
         reader.close();
         String json_data = stringBuilder.toString();

        try {
            // Parse JSON data
            JSONArray jsonArray = new JSONArray(json_data);

            // Establish database connection
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "password";
            Connection connection = DriverManager.getConnection(url, user, password);

            // Insert data into database
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject accountObject = jsonArray.getJSONObject(i);
                int accountId = accountObject.getJSONObject("account_id").getInt("$numberInt");
                int limit = accountObject.getJSONObject("limit").getInt("$numberInt");
                JSONArray productsArray = accountObject.getJSONArray("products");

                // Insert account information
                PreparedStatement accountStatement = connection.prepareStatement("INSERT INTO accounts (account_id, limit_value) VALUES (?, ?)");
                accountStatement.setInt(1, accountId);
                accountStatement.setInt(2, limit);
                accountStatement.executeUpdate();

                // Insert or retrieve product IDs and associate them with the account
                for (int j = 0; j < productsArray.length(); j++) {
                    String productName = productsArray.getString(j);

                    // Insert product if not exists and retrieve its ID
                    PreparedStatement productStatement = connection.prepareStatement("INSERT INTO products (name) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM products WHERE name = ?) RETURNING product_id");
                    productStatement.setString(1, productName);
                    productStatement.setString(2, productName);
                    ResultSet productResultSet = productStatement.executeQuery();

                    int productId;
                    if (productResultSet.next()) {
                        productId = productResultSet.getInt(1);
                    } else {
                        // Retrieve product ID if product already exists
                        PreparedStatement getIdStatement = connection.prepareStatement("SELECT product_id FROM products WHERE name = ?");
                        getIdStatement.setString(1, productName);
                        ResultSet resultSet = getIdStatement.executeQuery();
                        resultSet.next();
                        productId = resultSet.getInt("product_id");
                    }

                    // Associate product with account
                    PreparedStatement associationStatement = connection.prepareStatement("INSERT INTO account_products (account_id, product_id) VALUES (?, ?)");
                    associationStatement.setInt(1, accountId);
                    associationStatement.setInt(2, productId);
                    associationStatement.executeUpdate();
                }
            }

            // Close connection
            connection.close();
            System.out.println("Data inserted successfully!");
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }
    }
}
