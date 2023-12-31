import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.Scanner;
import java.util.concurrent.Future;
import java.math.BigInteger;

public class CalculatingFactorial
{
    public static void main(String[] args)
    {
        Scanner object= new Scanner(System.in);
        System.out.println("Enter a Large Number to Calculate factorial: ");
        long inputNum=object.nextLong();

        ExecutorService executorService=Executors.newFixedThreadPool(3);
        Future<BigInteger> future = executorService.submit(new Task(inputNum));
        System.out.println("Calculation in progress...");

        try {
            System.out.println("The Factorial of the given number is--> " + future.get());
        }catch(Exception e){
            System.err.println("An error occurred during calculation: " + e.getMessage());
        } finally {
            object.close();
            executorService.shutdownNow();
        }

    }
    public static class Task implements Callable<BigInteger>
    {
        private long inputNum;

        public Task(long inputNum)
        {
            this.inputNum=inputNum;
        }


        public BigInteger call() throws Exception
        {
            Thread.sleep(3000);
            BigInteger factorial=BigInteger.ONE;
            for(int i=2;i<=inputNum;i++)
            {
                factorial=factorial.multiply(BigInteger.valueOf(i));
            }
            return factorial;

        }
    }

}
