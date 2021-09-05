import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class CountingThread extends TalkingThread implements Callable<Integer> {
    private final int MAX_TALK;
    private int counter = 0;

    public CountingThread(TalkingThread baseThread, int MAX_TALK) {
        super(null, baseThread.getName(), baseThread.MIN_DELAY, baseThread.MAX_DELAY);
        this.MAX_TALK = MAX_TALK;
    }

    public CountingThread(String name, long MIN_DELAY, long MAX_DELAY, int MAX_TALK) {
        super(null, name, MIN_DELAY, MAX_DELAY);
        this.MAX_TALK = MAX_TALK;
    }

    @Override
    protected void doingWork() throws InterruptedException {
        if (counter == MAX_TALK) return;
        super.doingWork();
        counter++;
    }

    @Override
    public Integer call() {
        run();
        return counter;
    }

    public static List<CountingThread> demoSet(int maxTalk) {
        return Arrays.stream( demoSet() )
                .map(talkingThread -> new CountingThread(talkingThread, maxTalk))
                .collect(Collectors.toCollection(() -> new ArrayList<>(demoSet().length)));
    }

}
