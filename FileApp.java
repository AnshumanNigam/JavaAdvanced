import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CompletableFuture;
public class FileApp { // Main class

    public static void main(String[] args) {
        String[] filePaths = {"file1.txt", "file2.txt", "file3.txt"};
        FileApp fileApp = new FileApp();
        fileApp.processFiles(filePaths);
    }

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void processFiles(String[] filePaths) {
        FileProcessor[] fileProcessors = new FileProcessor[filePaths.length];
        for (int i = 0; i < filePaths.length; i++) {
            fileProcessors[i] = new FileProcessor();
        }

        CompletableFuture<String>[] futures = new CompletableFuture[filePaths.length];
        for (int i = 0; i < filePaths.length; i++) {
            futures[i] = fileProcessors[i].processFile(filePaths[i]).thenApplyAsync(result -> {
                // Add additional processing if needed
                return result;
            }, executorService);
        }

        StringBuilder summaryReport = new StringBuilder("Summary Report:\n");
        CompletableFuture.allOf(futures).join(); // Wait for all tasks to complete
        for (int i = 0; i < futures.length; i++) {
            try {
                summaryReport.append(futures[i].get()).append("\n");
            } catch (Exception e) {
                summaryReport.append("Error processing file: ").append(filePaths[i]).append("\n");
                e.printStackTrace();
            }
        }
        System.out.println(summaryReport);

        executorService.shutdown(); // Shutdown the executor service
    }
}

class FileProcessor { // Separate class for file processing logic

    public CompletableFuture<String> processFile(String filePath) {
        // Simulate file processing (replace with your actual logic)
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // Simulate processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Processed result for " + filePath;
        });
    }
}

