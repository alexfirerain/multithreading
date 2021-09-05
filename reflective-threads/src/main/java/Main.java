import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    final static int TALK_LIMIT = 5;

    public static void main(String[] args) {
        List<CountingThread> tasks = CountingThread.demoSet(TALK_LIMIT);
        ExecutorService chatPool = Executors.newFixedThreadPool(tasks.size());
        // использование `Runtime.getRuntime().availableProcessors()` на машине с меньшим количеством ядер
        // приводит к тому, что слово даётся только первым потокам!
        try {
            final Integer result = chatPool.invokeAny(tasks);
            System.out.println("Одним из потоков достигнут предел повторений: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            chatPool.shutdownNow();
        }
    }
}
