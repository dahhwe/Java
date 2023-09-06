import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Класс студента, он наследуется от класса Thread, чтобы этот класс был потоком.
 */

public class Student extends Thread {

    /**
     * Поток вывода данных
     */
    private final static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    /**
     * Стол экзаменатора (один для всех студентов).
     */
    private final Examiner examiner;

    /**
     * Номер студента.
     */
    private final int studentID;

    /**
     * Конструктор экземпляра студента.
     */
    public Student(int studentID, Examiner examiner) {
        this.studentID = studentID;
        this.examiner = examiner;

    }

    /**
     * Код красного цвете.
     */
    private static final String RED = "\033[1;31m";

    /**
     * Код жёлтого цвета.
     */
    private static final String YELLOW = "\u001B[33m";

    /**
     * Код зелёного цвета.
     */
    private static final String GREEN = "\u001B[32m";

    /**
     * Код фиолетового цвета.
     */
    private static final String PURPLE = "\u001B[35m";

    /**
     * Код для сброса раскраски.
     */
    private static final String RESET = "\u001B[0m";

    /**
     * Возвращает случайное число с заданным диапазоном.
     *
     * @param min Минимальное значение диапазона.
     * @param max Максимальное значение диапазона.
     * @return Случайное число с заданным диапазоном.
     */
    public static int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

    private void getResultsData(Integer result) {
        if (result == 1) {
            out.printf("%sЭкзаменатор ставит студенту #%d оценку %d%s\n", GREEN, studentID + 1,
                    randomInt(3, 5), RESET);
        } else {
            out.printf("%sЭкзаменатор выгоняет студента #%d!%s \n", RED, studentID + 1, RESET);
        }
    }

    /**
     * Процесс сдачи экзамена выполняющийся в потоке исполнения.
     */
    @Override
    public void run() {
        boolean examPassed = false;

        out.printf("Студент #%d сидит за партой и повторяет\n", studentID + 1);

        while (!examPassed) {
            if (!examiner.getExaminerIsFree()) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }

                out.printf("Студент #%d готов к сдаче и ожидает преподавателя\n", studentID + 1);

                continue;
            }
            synchronized (examiner) {
                examiner.setExaminerIsFree(false);
                out.printf("%sСтудент #%d подходит к экзаменатору и приступает к сдаче экзамена%s\n", YELLOW,
                        studentID + 1, RESET);
                try {
                    int result = randomInt(1, 3);
                    Thread.sleep(2000);

                    examiner.setExaminerIsFree(false);
                    if (result == 3) {
                        out.printf("%sЭкзаменатор отправляет студента #%d еще раз подумать за парту%s\n", PURPLE,
                                studentID + 1, RESET);
                        out.printf("Студент #%d готовится к сдаче экзамена\n", studentID + 1);

                        examiner.setExaminerIsFree(true);
                        try {
                            examiner.wait(2000);
                            examiner.notifyAll();

                        } catch (InterruptedException ignored) {
                        }
                        Thread.sleep(2000);
                        out.printf("%sСтудент #%d подходит к экзаменатору и приступает к сдаче экзамена%s\n", YELLOW,
                                studentID + 1, RESET);
                        getResultsData(randomInt(1, 2));

                    } else {
                        getResultsData(result);
                    }
                    examPassed = true;
                    examiner.setExaminerIsFree(true);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
