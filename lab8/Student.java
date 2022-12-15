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
    Student(int studentID, Examiner examiner) {
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

    /**
     * Процесс сдачи экзамена выполняющийся в потоке исполнения.
     */
    @Override
    public void run() {
        boolean examPassed = false;

        out.printf("Студент #%d сидит за партой и повторяет\n", studentID + 1);

        while (!examPassed) {
            synchronized (examiner) {
                if (examiner.getExaminerIsFree()) {
                    examiner.setExaminerIsFree(false);
                    out.printf("%sСтудент #%d подходит к экзаменатору и приступает к сдаче экзамена%s\n", YELLOW,
                            studentID + 1, RESET);

                    try {
                        Thread.sleep(2000);

                        int result = randomInt(1, 3);

                        if (result == 1) {
                            out.printf("%sЭкзаменатор ставит студенту #%d оценку %d%s\n", GREEN, studentID + 1,
                                    randomInt(3, 5), RESET);
                            examPassed = true;
                            examiner.setExaminerIsFree(true);

                        } else if (result == 2) {
                            out.printf("%sЭкзаменатор выгоняет студента #%d!%s \n",
                                    RED, studentID + 1, RESET);
                            examPassed = true;
                            examiner.setExaminerIsFree(true);

                        } else if (result == 3) {
                            out.printf("%sЭкзаменатор отправляет студента #%d еще раз подумать за парту%s\n",
                                    PURPLE, studentID + 1, RESET);

                            out.printf("Студент #%d готовится к сдаче экзамена\n", studentID + 1);

                            examiner.setExaminerIsFree(true);

                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ignored) {
                            }
                        }
                    } catch (InterruptedException ignored) {
                    }
                } else {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
    }
}
