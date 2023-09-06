import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Класс экзаменатора.
 */
public class Examiner {

    /**
     * Является ли экзаменатор свободным.
     */
    private final AtomicBoolean examinerIsFree = new AtomicBoolean(true);

    /**
     * Конструктор без параметров.
     */
    public Examiner() {
    }

    /**
     * Возвращает доступность экзаменатора для студентов.
     *
     * @return Доступность экзаменатора для студентов.
     */
    public boolean getExaminerIsFree() {
        return examinerIsFree.get();
    }

    /**
     * Устанавливает доступность экзаменатора для студентов.
     *
     * @param newValue Доступность экзаменатора для студентов
     */
    public void setExaminerIsFree(boolean newValue) {
        examinerIsFree.set(newValue);
    }
}
