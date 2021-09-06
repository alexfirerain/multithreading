/**
 * Класс-носитель статических методов генерации массива
 * случайных целочисленных значений.
 *
 * В качестве обязательного аргумента передаётся длина требуемого массива.
 *
 * Для явного определения минимального и максимального значения
 * элементов можно передать ещё два аргумента,
 * меньший из которых трактуется как минимальное, а больший как максимальное.
 *
 * При передаче только одного дополнительного аргумента,
 * второй принимается равным '0', их назначение трактуется аналогично.
 *
 * При отсутствии дополнительных аргументов,
 * минимальный принимается равным '0', второй - '255'.
 */

public class ArrGenerator {

    public static Integer[] generate(int length, int MIN, int MAX) {
        int min = Math.min(MIN, MAX);
        int max = Math.max(MIN, MAX);
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++)
            arr[i] = (int) (Math.random() * (max - min)) + min;
        return arr;
    }

    public static Integer[] generate(int length, int MAX) {
        return generate(length, 0, MAX);
    }

    public static Integer[] generate(int length) {
        return generate(length, 255);
    }
}
