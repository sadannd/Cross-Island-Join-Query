package bigdawg.bigdawg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JoinQueryPSQL_Accumulo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String curlCommand = "curl -X POST -d \"bdtext({ 'op' : 'scan', 'table' : 'TABLE_NAME'});\" http:/IP_ADDRESS:8080/bigdawg/query/>fileName.txt";

		ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", curlCommand);

        //ProcessBuilder processBuil der = new ProcessBuilder("/bin/bash", "-c", curlCommand);
        processBuilder.redirectErrorStream(true);
        try {
			Process process = processBuilder.start();
			process.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
        Set<String> columnNames = new HashSet<>();

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("fileName.txt"))) {
            // Read lines and add them to the list
            String line;
            while ((line = br.readLine()) != null) {
            	 String[] parts = line.split("\t");
                 if (parts.length > 1) {
                     String columnName = parts[1].split(":")[1];
                     if(columnNames.contains(columnNames))
                     {
                    	 break;
                     }
                    
                     columnNames.add(columnName);
                 }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        curlCommand = "curl -X POST -d \"bdrel(select * from TABLE_NAME;)\""+" http:/IP_ADDRESS:8080/bigdawg/query/>attribute.txt";
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
            String line;
			while ((line = br.readLine()) != null) {
                String[] parts_attributes = line.split("\t");
                attributeName_desc.put(parts_attributes[2].trim(), parts_attributes[4].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(attributeName_desc);
        
        Set<String> commonElements = new HashSet<>(attributeName_desc.keySet());
        commonElements.retainAll(columnNames);
        
        //System.out.println("Common Elements:");

        for (String commonElement : commonElements) {
            System.out.println(commonElement+"-"+attributeName_desc.get(commonElement));
        }
        System.out.println();
        for(int i=0;i<lines.size();i++)
        {
            System.out.println(lines.get(i));

        }
	}

}
