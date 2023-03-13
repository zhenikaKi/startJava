import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Задание 1 (Дополнительное): Реализовать алгоритм сортировки слиянием.<br>
 * Задание 2: Пусть дан произвольный список целых чисел, удалить из него чётные числа.<br>
 * Задание 3: Задан целочисленный список ArrayList. Найти минимальное, максимальное и среднее ариф. из этого списка.
 */
public class Lesson3 {

	public static void main(String[] args) {
		//task1();
		//task2();
		task3();
	}

	private static void task1() {
		List<Integer> array = generateRandomIntArray(10);
		System.out.printf("Исходный массив:\n%s\n", array);

		array = lessonMergeSort(array);
		System.out.printf("Отсортированный массив:\n%s\n", array);
	}

	private static void task2() {
		List<Integer> array = generateRandomIntArray(10);
		System.out.printf("Исходный массив:\n%s\n", array);

		int ind = 0;
		while (ind < array.size()) {
			if (array.get(ind) % 2 == 0) {
				array.remove(ind);
				ind = 0;
				continue;
			}

			ind++;
		}
		System.out.printf("Массив без четных чисел:\n%s\n", array);
	}

	private static void task3() {
		List<Integer> array = generateRandomIntArray(5);
		System.out.printf("Исходный массив:\n%s\n", array);

		int sumArray = 0;
		int minArray = array.get(0);
		int maxArray = array.get(0);
		for (int value: array) {
			if (value < minArray) {
				minArray = value;
			}
			if (value > maxArray) {
				maxArray = value;
			}
			sumArray += value;
		}
		System.out.printf("Минимальное значение: %d\n"
				+ "Максимальное значение: %d\n"
				+ "Среднее арифметическое значение: %.2f\n",
				minArray,
				maxArray,
				((double)sumArray / array.size()));
	}

	/**
	 * Сформировать целочисленный массив со случайными значениями.
	 * @param size Размер массива.
	 * @return Сформированный массив.
	 */
	private static List<Integer> generateRandomIntArray(int size) {
		Random random = new Random();
		List<Integer> array = new ArrayList<>();
		for (int ind = 0; ind < size; ind++) {
			array.add(random.nextInt(10));
		}
		return array;
	}

	/**
	 * Отсортировать массив сортировкой слиянием.
	 * @param array Массив, который нужно отсортировать.
	 * @return Отсортированный массив.
	 */
	private static List<Integer> lessonMergeSort(List<Integer> array) {
		int size = array.size();
		if (size <= 1) {
			return array;
		}

		//разделяем массивы циклом, чтобы они были доступны для изменения
		List<Integer> leftArray = new ArrayList<>();
		List<Integer> rightArray = new ArrayList<>();
		int centerInd = size / 2;
		for (int ind = 0; ind < centerInd; ind++) {
			leftArray.add(array.get(ind));
			int rightInd = centerInd + ind;
			if (rightInd < size) {
				rightArray.add(array.get(rightInd));
			}
		}
		if (size % 2 != 0) {
			rightArray.add(array.get(size -1));
		}
		leftArray = lessonMergeSort(leftArray);
		rightArray = lessonMergeSort(rightArray);
		return mergeArrays(leftArray, rightArray);
	}

	/**
	 * Объединить два отсортированных массива в один для алгоритма сортировки слиянием.
	 * @param leftArray Первый массив для объединения.
	 * @param rightArray Второй массив для объединения.
	 * @return Объединенный массив.
	 */
	private static List<Integer> mergeArrays(List<Integer> leftArray, List<Integer> rightArray) {
		final int FIRST_INDEX = 0;

		List<Integer> result = new ArrayList<>();
		while (!leftArray.isEmpty() && !rightArray.isEmpty()) {
			if (leftArray.get(FIRST_INDEX) < rightArray.get(FIRST_INDEX)) {
				result.add(leftArray.get(FIRST_INDEX));
				leftArray.remove(FIRST_INDEX);
			}
			else {
				result.add(rightArray.get(FIRST_INDEX));
				rightArray.remove(FIRST_INDEX);
			}
		}

		//после поэлементного сравнения добавляем оставшиеся массивы в ответ
		if (!leftArray.isEmpty()) {
			result.addAll(leftArray);
		}
		if (!rightArray.isEmpty()) {
			result.addAll(rightArray);
		}

		return result;
	}
}
