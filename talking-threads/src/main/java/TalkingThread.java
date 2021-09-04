import java.util.Date;

public class TalkingThread extends Thread {
    final private long MIN_DELAY,
                       MAX_DELAY;

    @Override
    public void run() {
        try {
            while (!isInterrupted())
                doingWork();
        } catch (InterruptedException ignored) {}
        finally {
            System.out.printf("%s\tПоток %s ЗАВЕРШЁН%n", new Date(), getName());
        }
    }

    public TalkingThread(ThreadGroup group, String name, long MIN_DELAY, long MAX_DELAY) {
        super(group, name);
        this.MIN_DELAY = MIN_DELAY;
        this.MAX_DELAY = MAX_DELAY;
    }

    protected void doingWork() throws InterruptedException {
        Thread.sleep(randomDelay());
        System.out.println(new Date() + "\tПривет, я поток " + getName() + "!");
    }

    private long randomDelay() {
        return (long) (Math.random() * (MAX_DELAY - MIN_DELAY)) + MIN_DELAY;
    }
}
