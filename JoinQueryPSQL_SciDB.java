package bigdawg.bigdawg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JoinQueryPSQL_SciDB {

	public static void main(String[] args) {

        String curlCommand = "curl -X POST -d \"bdarray(filter(events2,i<4));\""+" http:/192.168.0.72:8080/bigdawg/query/>scidb.txt";
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", curlCommand);

        //ProcessBuilder processBuil der = new ProcessBuilder("/bin/bash", "-c", curlCommand);
        processBuilder.redirectErrorStream(true);
        try {
			Process process = processBuilder.start();
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
        
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("scidb.txt"))) {
            // Read lines and add them to the list
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String line;
        String[] parts = lines.get(0).split("\\s+");

        System.out.println();
        
        
        curlCommand = "curl -X POST -d \"bdrel(select * from attributes1;)\""+" http:/192.168.0.72:8080/bigdawg/query/>attribute.txt";
        processBuilder = new ProcessBuilder("cmd.exe", "/c", curlCommand);

        //ProcessBuilder processBuil der = new ProcessBuilder("/bin/bash", "-c", curlCommand);
        processBuilder.redirectErrorStream(true);
        try {
			Process process = processBuilder.start();
			process.waitFor();
	        
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
        
        HashMap<String, String> attributeName_desc = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("attribute.txt"))) {
            while ((line = br.readLine()) != null) {
                String[] parts_attributes = line.split("\t");
                attributeName_desc.put(parts_attributes[2].trim(), parts_attributes[4].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("attributeName   |   Description\n");
        for(int i=0;i<parts.length;i++)
        {
        	if(attributeName_desc.containsKey(parts[i]))
        	{
        		System.out.println(parts[i]+"-"+attributeName_desc.get(parts[i]));
        	}
        }
       System.out.println();
        for(int i=0;i<lines.size();i++)
        {
        	System.out.println(lines.get(i));
        }
       
	}
	

}
