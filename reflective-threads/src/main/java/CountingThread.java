import java.util.concurrent.Callable;

public class CountingThread extends TalkingThread implements Callable<Integer> {
    private final int MAX_TALK;
    private int counter = 0;
//    public CountingThread(ThreadGroup group, String name, long MIN_DELAY, long MAX_DELAY) {
//        super(group, name, MIN_DELAY, MAX_DELAY);
//    }
    public CountingThread(String name, long MIN_DELAY, long MAX_DELAY, int MAX_TALK) {
        super(null, name, MIN_DELAY, MAX_DELAY);
        this.MAX_TALK = MAX_TALK;
    }

    @Override
    protected void doingWork() throws InterruptedException {
        if (counter >= MAX_TALK)
            throw new InterruptedException();
        super.doingWork();
        counter++;
    }

    @Override
    public Integer call() {
        return counter;
    }

}
