/**
 * Проверяемое исключение, которое вызывается при вводе числа, лежащего вне диапазона.
 */
public class RangeException extends Exception {
    /**
     * Конструктор, который создаёт объект исключения.
     * @param message - описание ошибки.
     */
    RangeException(String message) {
        super(message);
    }
}
