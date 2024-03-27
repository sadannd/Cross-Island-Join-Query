package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgresFetcher implements DatabaseFetcher{

	@Override
	public List<String[]> fetchData(String query) {
	
	
		List<String[]> list=new ArrayList<String[]>();
		// TODO Auto-generated method stub
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

            // Execute a simple SELECT query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM attribute");
            

            // Process the result set
            while (resultSet.next()) {
                // Access data from the result set
                int catalog_id = resultSet.getInt("roll");
                //int object_id = resultSet.getInt("object_id");
                //String attributeName = resultSet.getString("attributeName");
                //String datatype = resultSet.getString("datatype");
                //String description = resultSet.getString("description");


                // Print the results
                System.out.println("ID: " + catalog_id + ", Name: " );
                list.add(new String[]{String.valueOf(catalog_id)});
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
	}
     catch(Exception e)
     {
        	
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
