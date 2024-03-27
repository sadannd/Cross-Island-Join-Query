package mypackage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.scidb.jdbc.IResultSetWrapper;


public class SciDBFetcher implements DatabaseFetcher{

	@Override
	public List<String[]> fetchData(String query) {
		List<String[]> list=new ArrayList<String[]>();

		// TODO Auto-generated method stub
		try
		{
			Class.forName("org.scidb.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Driver is not in the CLASSPATH -> " + e);
		}

		try
		{
			System.out.print("here");
			//jdbc:postgresql://localhost:5432/postgres
			//http://jdbc:scidb://localhost:8081/select * from A
			Connection conn = DriverManager.getConnection("jdbc:scidb://localhost:8081");
			Statement st = conn.createStatement();
			System.out.print("here1");
			// create array A<a:string>[x=0:2,3,0, y=0:2,3,0];
			// select * into A from
			// array(A, '[["a","b","c"]["d","e","f"]["123","456","789"]]');
			ResultSet res = st.executeQuery("select * from m4x4_missing");

			//ResultSet res = st.executeQuery("select * from array(<a:string>[x=0:2,3,0, y=0:2,3,0],'[[\"a\",\"b\",\"c\"][\"d\",\"e\",\"f\"][\"123\",\"456\",\"789\"]]')");
			/*
			 * ResultSetMetaData meta = res.getMetaData();
			 * 
			 * System.out.println("Source array name: " + meta.getTableName(0));
			 * System.out.println(meta.getColumnCount() + " columns:");
			 * 
			 * IResultSetWrapper resWrapper = res.unwrap(IResultSetWrapper.class); for (int
			 * i = 1; i <= meta.getColumnCount(); i++) {
			 * System.out.println(meta.getColumnName(i) + " - " + meta.getColumnTypeName(i)
			 * + " - is attribute:" + resWrapper.isColumnAttribute(i)); }
			 * System.out.println("=====");
			 * 
			 * System.out.println("x y a"); System.out.println("-----");
			 * while(!res.isAfterLast()) { System.out.println(res.getLong("x") + " " +
			 * res.getLong("y") + " " + res.getString("a")); res.next(); }
			 */
		}
		catch (SQLException e)
		{
			System.out.println(e);
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
