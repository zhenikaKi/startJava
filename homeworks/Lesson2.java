package homeworks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.*;

/**
 * Задание 1: Реализуйте алгоритм сортировки пузырьком числового массива (введён вами),
 * результат после каждой итерации запишите в лог-файл.<br>
 * <br>
 * Задание 2: Дана строка (получение через обычный текстовый файл!!!)<br> *
 * "фамилия":"Иванов","оценка":"5","предмет":"Математика"<br>
 * "фамилия":"Петрова","оценка":"4","предмет":"Информатика"<br>
 * <br>
 * Написать метод(ы), который распарсит строку и, используя StringBuilder, создаст строки вида:<br>
 * Студент [фамилия] получил [оценка] по предмету [предмет].<br>
 * <br>
 * Пример вывода:<br>
 * Студент Иванов получил 5 по предмету Математика.<br>
 * Студент Петрова получил 4 по предмету Информатика.<br>
 * Студент Краснов получил 5 по предмету Физика.<br>
 * <br>
 * Задание 3: Напишите метод, который принимает на вход строку (String) и определяет, является ли строка палиндромом
 * (возвращает boolean значение). Без встр. функций
 * Задание 4 (дополнительно): К калькулятору из предыдущего дз добавить логирование.
 * 1 + 3 = 4
 */
public class Lesson2 {

    private static final Logger logger = Logger.getLogger(Lesson2.class.getName());

    public static void main(String[] args) throws IOException {
        FileHandler fileHandler = new FileHandler("log/lesson2.log");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);

        //task1();
        //task2();
        task3();
    }

    private static void task1() {
        logger.info("Задание 1. Начало");
        Scanner scanner = new Scanner(System.in, "Cp866");
        System.out.print("Введите элементы массива через пробел: ");
        String[] str = scanner.nextLine().split(" ");
        scanner.close();
        int[] arrayIn = stringArrayToIntArray(str);
        logger.info(String.format("Введенный массив: %s", Arrays.toString(arrayIn)));
        bubbleSort(arrayIn);
        logger.info(String.format("отсортированный массив: %s", Arrays.toString(arrayIn)));
        logger.info("Задание 1. Окончание");
    }

    private static void task2() {
        logger.info("Задание 2. Начало");
        try {
            //открываем файл
            File file = new File("homeworks/lesson2_2.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            //logger.info("Строки из файла");
            logger.info("Распарсенные строки");
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String parsedLine = parseLineAndPastInTemplate(line);
                logger.info(parsedLine);
            }
            bufferedReader.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Задание 2. Окончание");
    }

    private static void task3() {
        logger.info("Задание 3. Начало");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку для проверки: "); //а роза упала на лапу азора, шалаш, хассах
        String str = scanner.nextLine();
        scanner.close();
        logger.info(String.format("Фраза палиндром: %s", isPalindrom(str)));
        logger.info("Задание 3. Окончание");
    }

    /**
     * Проверить, является ли строка палиндромом (зеркальной).
     * @param str Строка для проверки.
     * @return true - строка является палиндроном, false - строка не палиндром.
     */
    private static boolean isPalindrom(String str) {
        String strTmp = str.replaceAll(" ", "");
        int size = strTmp.length();
        for (int ind = 0; ind < size / 2; ind++) {
            if (strTmp.charAt(ind) != strTmp.charAt(size - ind -1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Выполнить сортировку массива пузырьковым методом.
     * @param arrayIn Массив для сортировки.
     */
    private static void bubbleSort(int[] arrayIn) {
        for (int i = 0; i < arrayIn.length -1; i++) {
            for (int j = i+1; j < arrayIn.length; j++) {
                if (arrayIn[i] > arrayIn[j]) {
                    int tmp = arrayIn[i];
                    arrayIn[i] = arrayIn[j];
                    arrayIn[j] = tmp;
                }
            }
        }
    }

    /**
     * Преобразовать строковый массив к числовому.
     * @param str Строковый массив.
     * @return Преобразованный числовой массив.
     */
    private static int[] stringArrayToIntArray(String[] str) {
        int[] result = new int[str.length];
        for (int ind = 0; ind < str.length; ind++) {
            result[ind] = Integer.parseInt(str[ind]);
        }
        return result;
    }

    /**
     * Распарсить строку вида: "фамилия":"Иванов","оценка":"5","предмет":"Математика"
     * и привести ее к виду: Студент [фамилия] получил [оценка] по предмету [предмет].
     * Парсинг делается без регулярок и мапы. И считается, что на вход подается строка заданного вида.
     * @param str исходная строка для парсинга.
     * @return новая строка с данными из исходной строки.
     */
    private static String parseLineAndPastInTemplate(String str) {
        String[] strArray = str.split(",");
        //Массив пар. Каждая пара - это еще массив, в нем на первом месте ключ, на втором - значение
        String[][] keyValues = new String[strArray.length][2];
        //обходим все пары ключ-значение в строке
        for (int ind = 0; ind < strArray.length; ind++) {
            String[] keyValue = strArray[ind].split(":");
            keyValues[ind][0] = "\\[" + keyValue[0].replaceAll("\"", "") + "\\]";
            keyValues[ind][1] = keyValue[1].replaceAll("\"", "");
        }
        return pastInTemplate(keyValues);
    }

    /**
     * Подставить значения из массива в шаблонную строку: Студент [фамилия] получил [оценка] по предмету [предмет].
     * @param keyValues Массив значений.
     * @return Шаблонная строка с подставленными значениями.
     */
    private static String pastInTemplate(String[][] keyValues) {
        //вариант через замену
        /*String result = "Студент [фамилия] получил [оценка] по предмету [предмет]";
        for (String[] keyValue: keyValues) {
            result = result.replaceAll(keyValue[0], keyValue[1]);
        }
        return result;*/

        //вариант через StringBuilder
        StringBuilder result = new StringBuilder();
        for (int ind = 0; ind < keyValues.length; ind++) {
            switch (ind) {
                case 0:
                    result.append("Студент ");
                    break;
                case 1:
                    result.append(" получил ");
                    break;
                case 2:
                    result.append(" по предмету ");
                    break;
            }
            result.append(keyValues[ind][1]);
        }
        return result.toString();
    }
}
