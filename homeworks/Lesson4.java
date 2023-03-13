package homeworks;

import java.util.*;

public class Lesson4 {

    public static void main(String[] args) {
        task1();
        //task2();
        //task3();
    }

    /**
     * Пусть дан LinkedList с несколькими элементами. Реализуйте метод(не void), который вернет “перевернутый” список.
     */
    private static void task1() {
        Random random = new Random();
        //наполняем исходный массив случайными числами
        LinkedList<Integer> array = new LinkedList<>();
        for (int ind = 0; ind < 10; ind++) {
            array.add(random.nextInt(100));
        }
        System.out.printf("Исходный массив:\n%s\n", array);
        System.out.printf("Перевернутый массив:\n%s\n", getArrayToBack(array));
    }

    /**
     * Реализуйте очередь с помощью LinkedList со следующими методами:
     * enqueue() - помещает элемент в конец очереди,
     * dequeue() - возвращает первый элемент из очереди и удаляет его,
     * first() - возвращает первый элемент из очереди, не удаляя.
     */
    private static void task2() {

    }

    /**
     * В калькулятор добавьте возможность отменить последнюю операцию.
     * Дополнительно каскадная отмена - отмена нескольких операций
     * Пример:
     * Ввод: 1
     * Ввод: +
     * Ввод: 2
     * Вывод: 3
     * Ввод:+
     * Ввод:4
     * Вывод 7
     * Ввод:*
     * Ввод:3
     * Вывод 21
     * Отмена
     * Вывод 7
     * Отмена
     * Вывод 3
     * Ввод:-
     * Ввод:1
     * Вывод 2
     */
    private static void task3() {

    }

    /**
     * Перевернуть LinkedList массив.
     * @param array Массив для переворачивания.
     * @return Перевернутый массив.
     */
    private static List<Integer> getArrayToBack(LinkedList<Integer> array) {
        List<Integer> result = new ArrayList<>();
        Iterator<Integer> iterator = array.descendingIterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }
}
