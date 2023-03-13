import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Задание 1: Вычислить n-ое треугольного число(сумма чисел от 1 до n), а так же n! (произведение чисел от 1 до n).<br>
 * Ввод:5<br>
 * Треугольное число 1 + 2 + 3 + 4 + 5 = 15<br>
 * n! 1 * 2 * 3 * 4 * 5 = 120<br>
 * <br>
 * Задание 2: Вывести все простые числа от 1 до 1000<br>
 * <br>
 * Задание 3: Реализовать простой калькулятор (+ - / *): Ввод числа, Ввод знака, Ввод числа<br>
 * <br>
 * Задание 4: (ДОПОЛНИТЕЛЬНАЯ) Задано уравнение вида q + w = e, q, w, e >= 0.<br>
 * Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69 (пользователь).<br>
 * Требуется восстановить выражение до верного равенства.<br>
 * Предложить хотя бы одно решение или сообщить, что его нет.<br>
 * Ввод: 2? + ?5 = 69<br>
 * Вывод: 24 + 45 = 69
 */
public class Lesson1 {

	private static final Logger logger = Logger.getLogger(Lesson1.class.getName());
	private static final int MAX_VALUE = 1000; //максимальное значение для числа, чтобы найти решение уравнения 4 задачи

	public static void main(String[] args) throws IOException {
		FileHandler fileHandler = new FileHandler("lesson1.log");
		fileHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(fileHandler);

		//task1();
		//task2();
		task3();
		//task4();
	}

	private static void task1() {
		Scanner scanner = new Scanner(System.in, "Cp866");
		System.out.print("Введите N: ");
		int n = scanner.nextInt();
		scanner.close();

		int sum = 0;
		int fact = 1;

		for (int ind = 1; ind <= n; ind++) {
			sum += ind;
			fact *= ind;
		}

		System.out.printf("Треугольное число = %d\n", sum);
		System.out.printf("Факториал = %d", fact);
	}

	private static void task2() {
		System.out.println("Простые числа:");
		for (int number = 1; number <= 1_000; number++) {
			if (isSimple(number)) {
				System.out.printf("%d ", number);
			}
		}
	}

	private static void task3() {
		Scanner scanner = new Scanner(System.in, "Cp866");
		System.out.print("Введите первое число: ");
		double n1 = scanner.nextDouble();
		System.out.print("Введите операцию (один символ: +-*/): ");
		char oper = scanner.next().charAt(0);
		System.out.print("Введите второе число: ");
		double n2 = scanner.nextDouble();
		scanner.close();
		logger.info(String.format("Выполняемая операция: %s %s %s", n1, oper, n2));

		switch (oper) {
			case '+':
				logger.info(String.format("Сумма: %f\n", (n1 + n2)));
				break;
			case '-':
				logger.info(String.format("Разность: %f\n", (n1 - n2)));
				break;
			case '*':
				logger.info(String.format("Произведение: %f\n", (n1 * n2)));
				break;
			case '/':
				logger.info(String.format("Деление: %f\n", (n1 / n2)));
				break;
			default:
				logger.log(Level.WARNING, "Некорректная операция");
		}
	}

	private static void task4() {
		Scanner scanner = new Scanner(System.in, "Cp866");
		System.out.print("задайте уравнение вида q + w = e, где q, w, e >= 0: ");
		String input = scanner.nextLine();
		//String input = "2? + ? = 100";
		scanner.close();

		//сперва убираем пробелы
		input = input.replaceAll("\\s", "");
		//получаем первое число
		String[] tmp = input.split("\\+");
		String q = tmp[0];
		//получаем второе число и ответ
		tmp = tmp[1].split("=");
		String w = tmp[0];
		String e = tmp[1];

		//определяем диапазон перебора чисел. На первом месте - диапазон, на втором - пройденный диапазон
		int[] qEndIter = new int[] {getEndIter(q), 0};
		int[] wEndIter = new int[] {getEndIter(w), 0};
		int[] eEndIter = new int[] {getEndIter(e), 0};

		boolean anyOne = false;
		//пробуем найти решение с подстановкой чисел от 1 до MAX_VALUE
		//перебираем значения для первого слагаемого
		do {
			int qTmp = replaceQuestionSymbol(q, qEndIter[1]);
			qEndIter[1]++;

			//перебираем значения для второго слагаемого
			wEndIter[1] = 0;
			do {
				int wTmp = replaceQuestionSymbol(w, wEndIter[1]);
				wEndIter[1]++;

				//перебираем значения для суммы
				eEndIter[1] = 0;
				do {
					int eTmp = replaceQuestionSymbol(e, eEndIter[1]);
					eEndIter[1]++;

					//проверяем равенство
					boolean success = qTmp + wTmp == eTmp;
					if (success) {
						anyOne = true;
						System.out.printf("Найденное решение: %d + %d = %d\n", qTmp, wTmp, eTmp);
						//return; //если убрать этот выход, то покажутся все найденные решения
					}
				} while (eEndIter[1] < eEndIter[0]);
			} while (wEndIter[1] < wEndIter[0]);
		} while (qEndIter[1] < qEndIter[0]);

		if (!anyOne) {
			System.out.printf("Не получилось найти решение при переборе чисел от 1 до %d", MAX_VALUE);
		}
	}

	/**
	 * Проверить просто или нет число.
	 * @param number Число для проверки
	 * @return true - число простое, false - число не простое.
	 */
	private static boolean isSimple(int number) {
		for (int ind = 2; ind < number; ind++) {
			if (number % ind == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Получить конечное число для перебора значений, подставляемых в уравнение.
	 * @param value элемент уравнения, в котором возможно нужно делать перебор.
	 * @return Число переборов.
	 */
	private static int getEndIter(String value) {
		return value.contains("?") ? MAX_VALUE: 0;
	}

	/**
	 * Заменить в значении символ вопроса на число.
	 * @param value Значение, в котором нужно сделать замену.
	 * @param number Число, на которое нужно заменить символ вопроса.
	 * @return Значение с заменой, преобразованное к числу.
	 */
	private static int replaceQuestionSymbol(String value, int number) {
		return Integer.parseInt(value.replaceAll("\\?", String.valueOf(number)));
	}
}
