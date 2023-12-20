package bigdawg.bigdawg;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class Join_Query_Analyzer {
    public static void main(String[] args) {
        String directoryPath = "E:\\Final_Testing_Files_bigdawg_research\\PSQL_Scidb_join_query_simulation"; // Replace with the actual directory path

        File directory = new File(directoryPath);

        if (directory.isDirectory()) {
            // Iterate over all files in the directory
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        // Calculate the mean for each file
                        double meanExecutionTime = calculateMeanExecutionTime(file.getAbsolutePath());

                        // Print the result
                        System.out.println("Mean Execution Time for " + file.getName() + ": " + meanExecutionTime);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("Invalid directory path: " + directoryPath);
        }
    }
    private static double calculateMeanExecutionTime(String fileName) throws IOException {
        int totalExecutionTime = 0;
        int numberOfThreads = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Extract execution time from each line
                int executionTime = extractExecutionTime(line);

                // Check if the execution time is valid (not 0)
                if (executionTime > 0) {
                    // Accumulate total execution time and count the number of threads
                    totalExecutionTime += executionTime;
                    numberOfThreads++;
                }
            }
        }

        // Calculate the mean execution time
        if (numberOfThreads > 0) {
            return (double) totalExecutionTime / numberOfThreads;
        } else {
            return 0.0; // Avoid division by zero if there are no valid threads
        }
    }

    private static int extractExecutionTime(String line) {
        // Ignore empty lines
        if (line.trim().isEmpty()) {
            return 0;
        }

        try {
            // Extract the execution time from the line
            String[] tokens = line.split("\\s+");
            if (tokens.length >= 6 && tokens[tokens.length - 1].equals("milliseconds")) {
                return Integer.parseInt(tokens[tokens.length - 2]);
            } else {
               return 0;
            }
        } catch (Exception e) {
            System.err.println("Error processing line: " + line);
            throw e;
        }
    }

    // The rest of the code remains the same
    // ...

    // You can keep the other methods (calculateMeanExecutionTime, extractExecutionTime) unchanged.
}
