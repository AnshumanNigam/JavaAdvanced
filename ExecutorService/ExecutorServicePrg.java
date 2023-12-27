import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ExecutorServicePrg implements Runnable
{
    private String filepath;
    public ExecutorServicePrg(String filepath)
    {
        this.filepath=filepath;
    }
    public void run()
    {
        try{
            String str=String.join("", Files.readAllLines(Paths.get(filepath))).replaceAll("[^\\sa-zA-Z0-9]","");
            String[] words=str.split("\s+");
            System.out.println("Count for thread: "+Thread.currentThread().getName()+" is "+words.length);
        }catch(IOException ioe)
        {
            System.out.println(ioe);
        }
    }
    public static void main(String args[])
    {
        ExecutorService executorService=Executors.newFixedThreadPool(10);
        String Path="C:\\Users\\anshu\\OneDrive\\Desktop\\programming\\ExecutorService";
        executorService.submit(new ExecutorServicePrg(Path+"\\File1.txt"));
        executorService.submit(new ExecutorServicePrg(Path+"\\File2.txt"));
        executorService.submit(new ExecutorServicePrg(Path+"\\File3.txt"));
        executorService.submit(new ExecutorServicePrg(Path+"\\File4.txt"));
        executorService.submit(new ExecutorServicePrg(Path+"\\File5.txt"));
        executorService.shutdown();
    }
}
