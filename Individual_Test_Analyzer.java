package bigdawg.bigdawg;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Individual_Test_Analyzer {
    private static Map<String, Double> subdirectorySums = new HashMap<>();
    private static Map<String, Integer> subdirectoryCounts = new HashMap<>();

    public static void main(String[] args) {
        String mainDirectoryPath = "E:\\Final_Testing_Files_bigdawg_research\\Individual tests for accumulo, scidb, psql\\Scidb-events";

        try {
            Files.walkFileTree(Paths.get(mainDirectoryPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    // Process each file
                    if (Files.isRegularFile(file) && file.toString().endsWith(".txt")) {
                    	System.out.println(file.getFileName());
                        processFile(file.getParent(), file);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });

            // Calculate and print the mean for each subdirectory
            for (Map.Entry<String, Double> entry : subdirectorySums.entrySet()) {
                String subdirectory = entry.getKey();
                double sum = entry.getValue();
                int count = subdirectoryCounts.get(subdirectory);
                
                if (count > 0) {
                    double mean = sum / count;
                    System.out.println("Mean time_total for " + subdirectory + ": " + mean);
                } else {
                    System.out.println("No valid time_total values found in " + subdirectory);
                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void processFile(Path subdirectory, Path file) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
        	
            String line;
            while ((line = reader.readLine()) != null) {
                // Define the pattern to match time_total
                Pattern pattern = Pattern.compile("time_total:([\\d.]+)");

                // Create a matcher object
                Matcher matcher = pattern.matcher(line);

                // Check if the pattern is found in the current line
                if (matcher.find()) {
                    // Extract the time_total value
                    String timeTotalValue = matcher.group(1);

                    // Add the value to the subdirectory sum
                    subdirectorySums.merge(subdirectory.toString(), Double.parseDouble(timeTotalValue), Double::sum);

                    // Increment the subdirectory count
                    subdirectoryCounts.merge(subdirectory.toString(), 1, Integer::sum);
                }
            }
        }
    }
}
