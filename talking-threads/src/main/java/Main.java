import java.util.Date;

public class Main {
    private static final long TIME_TO_TALK = 20_000;

    public static void main(String[] args) {
        ThreadGroup chatRoom = new ThreadGroup("Переговорная");
        System.out.println(new Date() + "\tКОМНАТА ОТКРЫТА");

        TalkingThread a = new TalkingThread(chatRoom, "первый", 1800, 3800);
        a.start();
        TalkingThread b = new TalkingThread(chatRoom, "второй", 1500, 4200);
        b.start();
        TalkingThread c = new TalkingThread(chatRoom, "третий", 2500, 3500);
        c.start();
        TalkingThread d = new TalkingThread(chatRoom, "четвёртый", 1800, 2500);
        d.start();

        try {
            Thread.sleep(TIME_TO_TALK);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        chatRoom.interrupt();
        System.out.println(new Date() + "\tКОМНАТА ЗАКРЫТА");
    }
}
