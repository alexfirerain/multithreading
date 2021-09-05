import java.text.MessageFormat;

public class Main {
    public static void main(String[] args) {
        int[] test_1 = generateMassive(1_000_000);

        System.out.println("Сумма = " + singleThreadSum(test_1));

    }

    public static long singleThreadSum(int[] array) {
        long l = System.nanoTime();
        long sum = 0;
        for (int i : array)
            sum += i;
        System.out.printf("Последовательный подсчёт занял %.3f мс\n",
                ((double) System.nanoTime() - l) / 1_000_000f);
        return sum;
    }

    public static int[] generateMassive(int length, int MIN, int MAX) {
        int min = Math.min(MIN, MAX);
        int max = Math.max(MIN, MAX);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++)
            arr[i] = (int) (Math.random() * (max - min)) + min;
        System.out.println("Сгенерирован случайный массив длиной " +
                length + " от " + min + " до " + max);
        return arr;
    }
    public static int[] generateMassive(int length, int MAX) {
        return generateMassive(length, 0, MAX);
    }
    public static int[] generateMassive(int length) {
        return generateMassive(length, 255);
    }
}
