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
            finalAction();
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

    private void finalAction() {
        System.out.printf("%s\tПоток %s ЗАВЕРШЁН%n", new Date(), getName());
    }

    private long randomDelay() {
        return (long) (Math.random() * (MAX_DELAY - MIN_DELAY)) + MIN_DELAY;
    }

    public static TalkingThread[] demoSet(ThreadGroup group) {
        return new TalkingThread[]{
                new TalkingThread(group, "первый", 1800, 3800),
                new TalkingThread(group, "второй", 1500, 4200),
                new TalkingThread(group, "третий", 2500, 3500),
                new TalkingThread(group, "четвёртый", 1800, 2500)
        };
    }
}
