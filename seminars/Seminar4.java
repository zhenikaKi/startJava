package seminars;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Seminar4 {
    public static void main(String[] args) {
        //task1();
        task2();
    }

    /**
     * Реализовать консольное приложение, которое:
     * 1. Принимает от пользователя строку
     * 2. сохранить text в связный список.
     * 3. Если введено print~num, выводит строку из позиции num в связном списке и удаляет её из списка.
     */
    private static void task1() {
        Scanner scan_line = new Scanner(System.in);
        LinkedList<String> arr_list = new LinkedList<>();

        while (true) {
            System.out.println(arr_list);
            System.out.print("Введите текст: ");
            String text = scan_line.next();
            if (text.contains("print~")) {
                int remove_idx = Integer.parseInt(text.split("~")[1]);
                try {
                    System.out.println(arr_list.get(remove_idx));
                    arr_list.remove(remove_idx);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                arr_list.add(text);
            }
        }
    }

    /**
     * Реализовать консольное приложение, которое:
     * 1. Принимает от пользователя и “запоминает” строки.
     * 2. Если введено print, выводит строки так, чтобы последняя введенная была первой в списке, а первая - последней.
     */
    private static void task2() {
        Scanner scan_line = new Scanner(System.in);
        Stack<String> arr_list = new Stack<>();
        String text = "";
        while (!text.contains("print")) {
            System.out.println(arr_list);
            System.out.print("Введите текст: ");
            text = scan_line.next();
            if (text.contains("print")) {
                while (!arr_list.isEmpty()) {
                    System.out.print(arr_list.pop());
                }
            } else {
                arr_list.push(text);
            }
        }
    }
}







