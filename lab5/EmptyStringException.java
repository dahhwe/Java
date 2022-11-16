/**
 * Проверяемое исключение, которое вызывается при вводе пустой строки.
 */
public class EmptyStringException extends Exception {
    /**
     * Конструктор, который создаёт объект исключения.
     * @param message - описание ошибки.
     */
    EmptyStringException(String message) {
        super(message);
    }
}
