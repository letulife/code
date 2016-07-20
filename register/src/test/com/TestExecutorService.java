import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenxidu on 7/11/16.
 */
public class TestExecutorService
{

    public  static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        {
            executorService.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    System.out.println("Asynchrous task");
                }
            });
        }

        executorService.shutdown();
    }
}
