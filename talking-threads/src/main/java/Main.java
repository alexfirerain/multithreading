import java.util.Date;

public class Main {
    private static final long TIME_TO_TALK = 20_000;

    public static void main(String[] args) {
        ThreadGroup chatRoom = new ThreadGroup("Переговорная");
        System.out.println(new Date() + "\t" + chatRoom.getName() + " ОТКРЫТА");

        for (TalkingThread thread : TalkingThread.demoSet(chatRoom))
            thread.start();

        try {
            Thread.sleep(TIME_TO_TALK);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        chatRoom.interrupt();
        System.out.println(new Date() + "\t" + chatRoom.getName() + " ЗАКРЫТА");
    }
}
