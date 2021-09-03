import java.util.Date;

public class TalkingThread extends Thread {
    long MIN_DELAY = 2500,
         MAX_DELAY = 3500;

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Thread.sleep(randomDelay());
                System.out.println( new Date() + "\tПривет, я поток " + getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long randomDelay() {
        return (long) (Math.random() * (MAX_DELAY - MIN_DELAY)) + MIN_DELAY;
    }
}
