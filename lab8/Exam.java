import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Экзамен, который сдают студенты.
 */
public class Exam {

    /**
     * Поток вывода данных.
     */
    private final static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    /**
     * Конструктор класса без параметров.
     */
    public Exam() {
    }

    /**
     * Создание необходимых потоков.
     *
     * @param numOfDesks Количество парт в аудитории.
     * @param examiner   Экзаменатор.
     * @return Список с созданными потоками.
     */
    private ArrayList<Student> createThreads(int numOfDesks, Examiner examiner) {

        ArrayList<Student> studentsThreads = new ArrayList<>();
        for (int x = 0; x < numOfDesks; x++) {
            Student student = new Student(x, examiner);
            studentsThreads.add(student);
        }
        return studentsThreads;
    }

    /**
     * Запуск всех студентов в аудиторию.
     *
     * @param numOfDesks Количество парт.
     * @throws InterruptedException Исключение прерывания потока.
     */
    public void examStart(int numOfDesks) throws InterruptedException {

        Examiner examiner = new Examiner();

        while (true) {
            ArrayList<Student> students = createThreads(numOfDesks, examiner);

            out.println("""
                    ╭─────────────────────────────────────────────────╮
                    │ Студенты заходят в аудиторию и садятся за парты │
                    ╰─────────────────────────────────────────────────╯""");

            students.forEach(Thread::start);
            try {
                for (Student thread : students) thread.join();
            } catch (InterruptedException ignored) {
            }

            out.print("""
                    ╭─────────────────────────────────────────────────────────╮
                    │ Аудитория освободилась, заходит новая группа студентов  │
                    ╰─────────────────────────────────────────────────────────╯
                    """);

//            students = createThreads(numOfDesks, examiner);
        }
    }
}