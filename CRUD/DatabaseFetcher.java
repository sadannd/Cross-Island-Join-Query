package mypackage;

import java.sql.Connection;
import java.util.List;

public interface DatabaseFetcher {
	
    List<String[]> fetchData(String query);
    Connection getConnection();
    void init();
    
    //SetData();
    //CRUD operations
    
}
	