/**
 * Проверяемое исключение, которое вызывается при вводе отрицательного числа.
 */
public class NegNumException extends Exception {
    /**
     * Конструктор, который создаёт объект исключения.
     * @param message - описание ошибки.
     */
    NegNumException(String message) {
        super(message);
    }
}
