package bigdawg.bigdawg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileGrep {
	static Map<Integer, Object[]> testData 
	= new TreeMap<Integer, Object[]>();
    public static void main(String[] args) {
    	int excelRow=0;
        String word1 = "size_";
        String word2 = "time_";
        String word3 = "speed";
        String directoryPath = "D:\\Exclipse automation testing for grep";
        File directory = new File(directoryPath);
        XSSFWorkbook workbook = new XSSFWorkbook(); 

		// spreadsheet object 
		XSSFSheet spreadsheet 
			= workbook.createSheet(" Student Data "); 

		// creating a row object 
		XSSFRow row; 

		// This data needs to be written (Object[]) 
		
        		if (directory.isDirectory()) {
        		    File[] files = directory.listFiles();

        		    if (files != null) {
        		        for (File file : files) {
        		            if (file.isFile()) {
        		                String filename=file.getName();
        		                String parentPath = file.getParent();
        		                String outputFilename = parentPath+"\\"+filename.replace(".", "_grep.");
        		                
        		                ArrayList<Double> array_size_download=new ArrayList<>();
        		                ArrayList<Double> array_size_upload=new ArrayList<>();
        		                ArrayList<Double> array_speed_download=new ArrayList<>();
        		                ArrayList<Double> array_speed_upload=new ArrayList<>();
        		                ArrayList<Double> array_time_connect=new ArrayList<>();
        		                ArrayList<Double> array_time_namelookup=new ArrayList<>();
        		                ArrayList<Double> array_time_pretransfer=new ArrayList<>();
        		                ArrayList<Double> array_time_starttransfer=new ArrayList<>();
        		                ArrayList<Double> array_time_total=new ArrayList<>();
        		                
        		                ArrayList<Double> average_size_download=new ArrayList<>();
        		                ArrayList<Double> average_size_upload=new ArrayList<>();
        		                ArrayList<Double> average_speed_download=new ArrayList<>();
        		                ArrayList<Double> average_speed_upload=new ArrayList<>();
        		                ArrayList<Double> average_time_connect=new ArrayList<>();
        		                ArrayList<Double> average_time_namelookup=new ArrayList<>();
        		                ArrayList<Double> average_time_pretransfer=new ArrayList<>();
        		                ArrayList<Double> average_time_starttransfer=new ArrayList<>();
        		                ArrayList<Double> average_time_total=new ArrayList<>();
        		                
        		                try {
        		                    // Open the input file for reading
        		                    BufferedReader reader = new BufferedReader(new FileReader(file));

        		                    // Open the output file for writing
        		                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));

        		                    String line;

        		                    // Read the input file line by line and search for the words
        		                    while ((line = reader.readLine()) != null) {
        		                        if (line.contains(word1) || line.contains(word2) || line.contains(word3)) {
        		                            String[] parts = line.split(":");
        		                            double extractedNumber = Double.parseDouble(parts[1].replace("\"", "").replace(",", "").trim());
        		                        	if(line.contains("size_download"))
        		                        	{
        		                        		array_size_download.add(extractedNumber);
        		                        	}
        		                        	else if(line.contains("size_upload"))
        		                        	{
        		                        		array_size_upload.add(extractedNumber);
        		                        	}
        		                        	else if(line.contains("speed_download"))
        		                        	{
        		                        		array_speed_download.add(extractedNumber);
        		                        	}
        		                        	else if(line.contains("speed_upload"))
        		                        	{
        		                        		array_speed_upload.add(extractedNumber);
        		                        	}
        		                        	else if(line.contains("time_connect"))
        		                        	{
        		                        		array_time_connect.add(extractedNumber);
        		                        	
        		                        	}
        		                        	else if(line.contains("time_namelookup"))
        		                        	{
        		                        		array_time_namelookup.add(extractedNumber);
        		                        	}
        		                        	else if(line.contains("time_pretransfer"))
        		                        	{
        		                        		array_time_pretransfer.add(extractedNumber);
        		                        	}
        		                        	else if(line.contains("time_starttransfer"))
        		                        	{
        		                        		array_time_starttransfer.add(extractedNumber);
        		                        	}
        		                        	else if(line.contains("time_total"))
        		                        	{
        		                        		array_time_total.add(extractedNumber);
        		                        	}
        		                        
        		                        	//System.out.println(line);
        		                            // Write the matching line to the output file
        		                            writer.write(line);
        		                            writer.newLine();
        		                        }
        		                    }
        		                	double sum_size_download=0;
        		                	double sum_size_upload=0;
        		                	double sum_speed_download=0;
        		                	double sum_speed_upload=0;
        		                	double sum_time_connect=0;
        		                	double sum_time_namelookup=0;
        		                	double sum_time_pretransfer=0;
        		                	double sum_time_starttransfer=0;
        		                	double sum_time_total=0;
        		                	
        		                	
        		                    int testNumber=array_size_download.size()/6;
        		                    testData.put((excelRow++),new Object[] {});

        		                    testData.put((excelRow++),new Object[] {});

        		                    testData.put((excelRow++),new Object[] {file.getName()});

        		                    testData.put((excelRow++),new Object[] {"tests",	"size_download"	,"size_upload","speed_download","speed_upload","time_connect","time_namelookup","time_pretransfer","time_starttransfer","time_total"});
        		                    for(int i=0;i<6;i++)
        		                    {
        		                    	
        		                    	sum_size_download=0;
        		                    	sum_size_upload=0;
        		                    	sum_speed_download=0;
        		                    	sum_speed_upload=0;
        		                    	sum_time_connect=0;
        		                    	sum_time_namelookup=0;
        		                    	sum_time_pretransfer=0;
        		                    	sum_time_starttransfer=0;
        		                    	sum_time_total=0;
        		                    	
        		                        for(int j=0;j<testNumber;j++)
        		                        {
        		                        	sum_size_download=sum_size_download+array_size_download.get(0);
        		                        	array_size_download.remove(0);
        		                        	
        		                        	sum_size_upload=sum_size_upload+array_size_upload.get(0);
        		                        	array_size_upload.remove(0);
        		                        	
        		                        	sum_speed_download=sum_speed_download+array_speed_download.get(0);
        		                        	array_speed_download.remove(0);
        		                        	
        		                        	sum_speed_upload=sum_speed_upload+array_speed_upload.get(0);
        		                        	array_speed_upload.remove(0);
        		                        	
        		                        	sum_time_connect=sum_time_connect+array_time_connect.get(0);
        		                        	array_time_connect.remove(0);
        		                        	
        		                        	sum_time_namelookup=sum_time_namelookup+array_time_namelookup.get(0);
        		                        	array_time_namelookup.remove(0);
        		                        	
        		                        	sum_time_pretransfer=sum_time_pretransfer+array_time_pretransfer.get(0);
        		                        	array_time_pretransfer.remove(0);
        		                        	
        		                        	sum_time_starttransfer=sum_time_starttransfer+array_time_starttransfer.get(0);
        		                        	array_time_starttransfer.remove(0);
        		                        	
        		                        	sum_time_total=sum_time_total+array_time_total.get(0);
        		                        	array_time_total.remove(0);
        		                        }
        		                        
        		                        average_size_download.add(sum_size_download/testNumber);
        		                        average_size_upload.add(sum_size_upload/testNumber);
        		                        average_speed_download.add(sum_speed_download/testNumber);
        		                        average_speed_upload.add(sum_speed_upload/testNumber);
        		                        average_time_connect.add(sum_time_connect/testNumber);
        		                        average_time_namelookup.add(sum_time_namelookup/testNumber);
        		                        average_time_pretransfer.add(sum_time_pretransfer/testNumber);
        		                        average_time_starttransfer.add(sum_time_starttransfer/testNumber);
        		                        average_time_total.add(sum_time_total/testNumber);
        		                        
            		                    testData.put((excelRow++),new Object[] {String.valueOf(i+1),average_size_download.get(i).toString(),
            		                    		average_size_upload.get(i).toString(),average_speed_download.get(i).toString(),average_speed_upload.get(i).toString(),average_time_connect.get(i).toString()
            		                    		,average_time_namelookup.get(i).toString(),average_time_pretransfer.get(i).toString(),average_time_starttransfer.get(i).toString(),average_time_total.get(i).toString()});

        		                    }
        		                    System.out.println(average_size_download);
        		                    System.out.println(average_size_upload);
        		                    System.out.println(average_speed_download);
        		                    System.out.println(average_speed_upload);
        		                    System.out.println(average_time_connect);
        		                    System.out.println(average_time_namelookup);
        		                    System.out.println(average_time_pretransfer);
        		                    System.out.println(average_time_starttransfer);
        		                    System.out.println(average_time_total);
        		                    
    		                    	sum_size_download=0;
    		                    	sum_size_upload=0;
    		                    	sum_speed_download=0;
    		                    	sum_speed_upload=0;
    		                    	sum_time_connect=0;
    		                    	sum_time_namelookup=0;
    		                    	sum_time_pretransfer=0;
    		                    	sum_time_starttransfer=0;
    		                    	sum_time_total=0;
    		                    	
    
    		                    	
									double final_median_size_download = (average_size_download.get(2)+average_size_download.get(3))/2;
									double final_median_size_upload = (average_size_upload.get(2)+average_size_upload.get(3))/2;
									double final_median_speed_download = (average_speed_download.get(2)+average_speed_download.get(3))/2;
									double final_median_speed_upload = (average_speed_upload.get(2)+average_speed_upload.get(3))/2;
									double final_median_time_connect = (average_time_connect.get(2)+average_time_connect.get(3))/2;
									double final_median_time_namelookup = (average_time_namelookup.get(2)+average_time_namelookup.get(3))/2;
									double final_median_time_pretransfer = (average_time_pretransfer.get(2)+average_time_pretransfer.get(3))/2;
									double final_median_time_starttransfer = (average_time_starttransfer.get(2)+average_time_starttransfer.get(3))/2;
									double final_median_time_total = (average_time_total.get(2)+average_time_total.get(3))/2;
    		                    	
    		                    	
    		                    	
        		                    for(int j=0;j<6;j++)
        		                    {
        		                    	sum_size_download=sum_size_download+average_size_download.get(0);
        		                    	average_size_download.remove(0);
        		                    	
        		                    	sum_size_upload=sum_size_upload+average_size_upload.get(0);
        		                    	average_size_upload.remove(0);
        		                    	
        		                    	sum_speed_download=sum_speed_download+average_speed_download.get(0);
        		                    	average_speed_download.remove(0);
        		                    	
        		                    	sum_speed_upload=sum_speed_upload+average_speed_upload.get(0);
        		                    	average_speed_upload.remove(0);
        		                    	
        		                    	sum_time_connect=sum_time_connect+average_time_connect.get(0);
        		                    	average_time_connect.remove(0);
        		                    	
        		                    	sum_time_namelookup=sum_time_namelookup+average_time_namelookup.get(0);
        		                    	average_time_namelookup.remove(0);
        		                    	
        		                    	sum_time_pretransfer=sum_time_pretransfer+average_time_pretransfer.get(0);
        		                    	average_time_pretransfer.remove(0);
        		                    	
        		                    	sum_time_starttransfer=sum_time_starttransfer+average_time_starttransfer.get(0);
        		                    	average_time_starttransfer.remove(0);
        		                    	
        		                    	sum_time_total=sum_time_total+average_time_total.get(0);
        		                    	average_time_total.remove(0);
        		                    }
        		                    
        		                  	double final_average_size_download=sum_size_download/6;
        		                	double final_average_size_upload=sum_size_upload/6;
        		                	double final_average_speed_download=sum_speed_download/6;
        		                	double final_average_speed_upload=sum_speed_upload/6;
        		                	double final_average_time_connect=sum_time_connect/6;
        		                	double final_average_time_namelookup=sum_time_namelookup/6;
        		                	double final_average_time_pretransfer=sum_time_pretransfer/6;
        		                	double final_average_time_starttransfer=sum_time_starttransfer/6;
        		                	double final_average_time_total=sum_time_total/6;
        		                	System.out.println("excel row is:-"+excelRow);
        		                	testData.put(excelRow++, new Object[] {});
        		                	testData.put((excelRow++),new Object[] {"Average",String.valueOf(final_average_size_download),
        		                			String.valueOf(final_average_size_upload),String.valueOf(final_average_speed_download),String.valueOf(final_average_speed_upload),
        		                			String.valueOf(final_average_time_connect),String.valueOf(final_average_time_namelookup)
        		                			,String.valueOf(final_average_time_pretransfer),String.valueOf(final_average_time_starttransfer),String.valueOf(final_average_time_total)
        		                    		});
        		                	
        		                	testData.put((excelRow++),new Object[] {"Median",String.valueOf(final_median_size_download),
        		                			String.valueOf(final_median_size_upload),String.valueOf(final_median_speed_download),String.valueOf(final_median_speed_upload),
        		                			String.valueOf(final_median_time_connect),String.valueOf(final_median_time_namelookup)
        		                			,String.valueOf(final_median_time_pretransfer),String.valueOf(final_median_time_starttransfer),String.valueOf(final_median_time_total)
        		                    		});

        		                    reader.close();
        		                    writer.close();

        		                } catch (IOException e) {
        		                    System.err.println(e.getMessage());
        		                }
        		            }
        		        }
        		    }
        		}
        	    Set<Integer> keyid = testData.keySet(); 

        		int rowid = 0; 

        		// writing the data into the sheets... 

        		for (Integer key : keyid) { 

        			row = spreadsheet.createRow(rowid++); 
        			Object[] objectArr = testData.get(key); 
        			int cellid = 0; 

        			for (Object obj : objectArr) { 
        				Cell cell = row.createCell(cellid++); 
        				cell.setCellValue((String)obj); 
        			} 
        		} 

        		// .xlsx is the format for Excel Sheets... 
        		// writing the workbook into the file... 
        		FileOutputStream out;
				try {
					out = new FileOutputStream( 
						new File("D://Exclipse automation testing for grep//GFGsheet.xlsx"));
					workbook.write(out); 
	        		out.close(); 
	        		System.out.println("Done");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

        	

    }

}
