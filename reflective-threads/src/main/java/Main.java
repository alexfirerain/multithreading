import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        List<CountingThread> tasks = CountingThread.demoSet(5);
        ExecutorService chatPool = Executors.newFixedThreadPool(4);
        try {
            System.out.println(chatPool.invokeAny( tasks ));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
