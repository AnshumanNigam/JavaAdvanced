public class ThreadsAltNumber {

    public static void main(String[] args) {
        Thread threadA = new NewThreads(1);
        Thread threadB = new NewThreads(2);

        threadA.start();
        threadB.start();
    }
}

class NewThreads extends Thread {
    private int startingNumber;

    public NewThreads(int startingNumber) {
        this.startingNumber= startingNumber;
    }

    @Override public void run()
    {
        if (startingNumber % 2 == 0)
        { try
        { Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        }
        for (int i = 0; i < 5; i++)
        {
            int product = i * 2;
            int number = startingNumber + product;
            System.out.print(number+" , ");
            try { Thread.sleep(2000);
            } catch (InterruptedException e)
            { e.printStackTrace();
            }
        }
    }
}