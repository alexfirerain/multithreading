import java.util.function.Function;

public class Tester {
    private final int[] arrLengths;
    private final int arrValMin;
    private final int arrValMax;
    private final int repetitions;
    private final StringBuilder report;

    public Tester(int[] arrLengths, int arrValMin, int arrValMax, int repetitions) {
        this.arrLengths = arrLengths;
        this.arrValMin = arrValMin;
        this.arrValMax = arrValMax;
        this.repetitions = repetitions;
        report = new StringBuilder();
        report.append("Будет проведено сравнительное тестирование двух реализаций суммирования массива: в один поток и рекурсивное.\n")
                .append("Будут сгенерированы массивы длиной: ");
        for (int i = 0; i < arrLengths.length; i++) {
            report.append(arrLengths[i]);

            if (i == arrLengths.length - 1)         report.append("; ");
            else if (i == arrLengths.length - 2)    report.append(" и ");
            else                                    report.append(", ");
        }
        report.append("заполнены они будут случайными целыми числами от %d до %d.\n"
                .formatted(arrValMin, arrValMax))
              .append("Для каждого массива будет исполнено по %d прогонов сначала одним методом, потом другим.\n\n"
                .formatted(repetitions));
    }

    private String executeSingleTest(Function<Integer[], Long> operation, Integer[] array, int repetitions) {
        long totalTestsDuration = 0;
        long maxDuration = 0;
        long minDuration = Long.MAX_VALUE;
        long sum = 0;

        for (int i = 0; i < repetitions; i++) {

            long l = System.nanoTime();
            sum = operation.apply(array);
            long duration = System.nanoTime() - l;

            if (duration > maxDuration) maxDuration = duration;
            if (duration < minDuration) minDuration = duration;
            totalTestsDuration += duration;
        }
        long averageDuration = Math.round((double) totalTestsDuration / (double) repetitions);

        return "Сумма %d вычислена за среднее время %s (минимальное %s, максимальное %s)\n"
                .formatted(sum,
                        nanoTimeFormatter(averageDuration),
                        nanoTimeFormatter(minDuration),
                        nanoTimeFormatter(maxDuration));
    }

    public void executeTesting() {
        for (int arrLength : arrLengths) {
            Integer[] testArray = ArrGenerator.generate(arrLength, arrValMin, arrValMax);
            report.append("Массив из %d элементов:\n".formatted(arrLength))
                    .append("\tОднопоточное суммирование:\n")
                    .append(executeSingleTest(singleThreadSum, testArray, repetitions))
                    .append("\tРекурсивное суммирование:\n")
                    .append(executeSingleTest(recursiveSum, testArray, repetitions))
                    .append("\n");
        }
        System.out.println(report.toString());
    }

    static Function<Integer[], Long> singleThreadSum = (Integer[] arr) -> {
        long sum = 0;
        for (int i : arr)
            sum += i;
        return sum;
    };

    static Function<Integer[], Long> recursiveSum = (Integer[] arr) -> (long) 0;

    private String nanoTimeFormatter(long duration) {
        if (duration < 10_000) return duration + " нс";
        return "%.3f мс".formatted((double) duration / 1_000_000f);
    }
}
