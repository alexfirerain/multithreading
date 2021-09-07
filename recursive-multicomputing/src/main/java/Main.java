public class Main {
    static final int[] TEST_ARR_SIZES = {
            10,
            100,
            1000,
            10_000,
            100_000,
            1_000_000,
            10_000_000,
//            100_000_000,
//            1_000_000_000
    };

    public static void main(String[] args) {
        System.out.println("""
                Тестовое сравнение двух способов суммирования.
                Данная реализация сначала всё считает, потом выводит.
                Это может занять некоторое время, к тому же может кончиться память.
                
                """);
        Tester tester = new Tester(TEST_ARR_SIZES, 0, 255, 10);
        tester.executeTesting();
    }

}
