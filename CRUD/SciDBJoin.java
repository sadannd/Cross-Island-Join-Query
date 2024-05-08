
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SciDBJoin {	
	public static void ShowScidbJoinData(String query)
	{
		Statement st;
		try {
			Connection conn = DriverManager.getConnection("jdbc:scidb://192.168.0.73:1239");

			st = conn.createStatement();
			ResultSet res = st.executeQuery(query);

			ResultSetMetaData meta = res.getMetaData();
			
			
			//System.out.println("Source array name: " + meta.getTableName(0));
			//System.out.println(meta.getColumnCount() + " columns:");
			//System.out.println("\n\n\n"+arrayName);
			for(int i=1;i<=meta.getColumnCount();i++)
			{
				System.out.print(meta.getColumnName(i));
			}
			while (!res.isAfterLast()) {
				
				for (int i = 1 ; i <= meta.getColumnCount(); i++ ) {
					Object value = null;
	            	switch (meta.getColumnTypeName(i).toLowerCase()) {
	            		case "int64":
	            			value = res.getLong(i);
	            			break;
	            		case "string":
	            			value = res.getString(i);
	            			break;
	            		case "float":
	            			value = res.getFloat(i);
	            			break;
	            		case "double":
	            			value = res.getDouble(i);
	            			break;
	            		case "datetime":
	            			value = res.getDate(i);
	            			break;
	            		case "bool":
	            			value = res.getBoolean(i);
	            			break;
	            		default:
	            			throw new SQLException("SciDB JDBC result set row retrieval does not support type: "
									+ meta.getColumnTypeName(i).toLowerCase());
	            	}
	            	System.out.printf("%s ", value);
				}
				System.out.println();
				res.next();
			}
			res.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public static void main(String[] args) throws ClassNotFoundException {
		try {
			Class.forName("org.scidb.jdbc.Driver");
			List<String> queries=new ArrayList<String>();
			//queries.add("SELECT * FROM users1 JOIN tier_details1 ON users1.customer_id = tier_details1.customer_id JOIN customers ON users1.customer_id = customers._id");
			//queries.add("SELECT * FROM accounts JOIN account_products ON accounts.account_id = account_products.account_id JOIN products ON account_products.product_id = products.product_id");
			queries.add("select * from transactions");
			for(int i=0;i<queries.size();i++)
			{
				ShowScidbJoinData(queries.get(i));
			}
			System.out.println("done");

			
			//ShowTableData("users1");
		} catch (ClassNotFoundException e) {
			System.out.println("here error");
			System.out.println("Driver is not in the CLASSPATH -> " + e);
		}

		
		System.exit(0);
	}

}

