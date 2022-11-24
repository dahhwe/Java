import java.util.*;

/**
 * Кольцевой двунаправленный список.
 *
 * @param <T> Дженерик-метод
 */
public class CircularDoublyLinkedList<T> {
    /**
     * Первый элемент списка
     */
    private Node head;
    /**
     * Последний элемент списка
     */
    private Node last;
    /**
     * Указатель
     */
    private Node pointer;
    /**
     * Длина списка
     */
    private int size;

    /**
     * Конструктор без параметров
     */
    CircularDoublyLinkedList() {
    }

    /**
     * Обнуление списка
     */
    public void clear() {
        this.head = null;
        this.last = null;
        this.pointer = null;
        this.size = 0;
    }

    /**
     * длина списка
     *
     * @return длина списка
     */
    public int size() {
        return this.size;
    }

    /**
     * Проверка является ли список пустым
     *
     * @return True / False в зависимости от значений списка
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Добавление элементов в список
     *
     * @param value Элемент для добавления
     */
    public void add(T value) {
        addLast(value);
    }

    /**
     * Добавление элемента в начало списка
     *
     * @param value Элемент для добавления
     */
    public void addFirst(T value) {
        if (value != null) {
            if (this.head == null) {
                this.head = new Node(value);
                this.head.prev = this.head.next = this.head;
                this.last = this.head;
            } else {
                Node node = new Node(this.head, this.last, value);
                this.last.next = this.head.prev = node;
                this.head = node;
            }
            this.size++;
        }
    }

    /**
     * Добавление элемента в конец списка
     *
     * @param value Элемент для добавления
     */
    public void addLast(T value) {
        if (value != null) {
            if (this.head == null) {
                addFirst(value);
            } else {
                Node node = new Node(this.head, this.last, value);
                this.head.prev = this.last.next = node;
                this.last = node;
                pointer = node;
                this.size++;
            }
        }
    }

    /**
     * Получение индекса указателя
     *
     * @return Индекс указателя
     */
    public int getPointerIndex() {
        if (this.pointer != null)
            return indexOf(pointer.value);
        else return 0;
    }

    /**
     * Добавление элемента по индексу
     *
     * @param index Индекс
     * @param value Элемент для добавления
     */
    public void addByIndex(int index, T value) {
        if (value != null) {
            if (index != 0) {
                checkIndex(index);
            }
            Node node = getNodeByIndex(index);
            if (node == null || node == this.head) {
                addFirst(value);
            } else if (node == this.last) {
                addLast(value);
            } else {
                Node addNode = new Node(node, node.prev, value);
                node.prev = node.prev.next = addNode;
                this.size++;
            }
        }
    }

    /**
     * Получение индекса элемента
     *
     * @param value Элемент
     * @return Индекс элемента
     */
    public int indexOf(T value) {
        int index = -1;
        if (value != null && !isEmpty()) {
            if (this.head.value.equals(value)) {
                index = 0;
            } else {
                int count = 1;
                for (Node node = this.head.next; node != this.head; node = node.next) {
                    if (node.value.equals(value)) {
                        index = count;
                        break;
                    } else {
                        count++;
                    }
                }
            }
        }
        return index;
    }

    /**
     * Проверка индекса на размерность
     *
     * @param index Индекс
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Incorrect index output...");
        }
    }

    /**
     * Получение связи по индексу
     *
     * @param index Индекс
     * @return связь
     */
    private Node getNodeByIndex(int index) {
        Node result = null;
        if (index == 0) {
            result = this.head;
        } else if (index == this.size - 1) {
            result = this.last;
        } else {
            if (this.size / 2 > index) {
                for (Node node = this.head.next; node != this.head; node = node.next) {
                    if (--index == 0) {
                        result = node;
                        break;
                    }
                }
            } else {
                index = this.size - index - 1;
                for (Node node = this.last.prev; node != this.last; node = node.prev) {
                    if (--index == 0) {
                        result = node;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Проверка индекса
     *
     * @param index Индекс
     * @return Проверенный индекс
     */
    public T get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).value;
    }

    /**
     * Удаление первого элемента
     */
    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element not found...");
        }
        T oldElement;
        if (this.size == 1) {
            oldElement = this.head.value;
            clear();
        } else {
            oldElement = this.head.value;
            Node newFirst = this.head.next;
            newFirst.prev = this.last;
            this.last.next = newFirst;
            this.head = newFirst;
            this.size--;
        }
    }

    /**
     * Удаление последнего элемента
     */
    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element not found...");
        }
        T oldElement;
        if (this.size == 1) {
            oldElement = this.head.value;
            clear();
        } else {
            oldElement = this.last.value;
            Node newLast = this.last.prev;
            newLast.next = this.head;
            this.head.prev = newLast;
            this.last = newLast;
            this.size--;
        }
    }

    /**
     * Удаление элемента
     *
     * @param value Элемент
     * @return Результат удаления
     */
    public boolean remove(T value) {
        boolean result;
        if (result = value != null) {
            if (result = !isEmpty()) {
                if (result = this.head.value.equals(value)) {
                    removeFirst();
                } else {
                    Node delete = null;
                    for (Node node = this.head.next; node != this.head; node = node.next) {
                        if (node.value.equals(value)) {
                            delete = node;
                        }
                    }
                    if (result = delete != null) {
                        if (delete == this.last) {
                            removeLast();
                        } else {
                            delete.prev.next = delete.next;
                            delete.next.prev = delete.prev;
                            this.size--;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Добавление элемента по индексу
     *
     * @param index Элемент
     * @param value Индекс
     */
    public void set(int index, T value) {
        if (value != null) {
            checkIndex(index);
            Node set = getNodeByIndex(index);
            set.value = value;
        }
    }

    /**
     * Установка указателя в начало списка
     */
    public void setPointerAtFront() {
        if (this.head == null) {
            throw new NoSuchElementException("Element not found...");
        }
        pointer = this.head;
    }

    /**
     * Установка указателя в конец списка
     */
    public void setPointerAtBack() {
        if (this.head == null) {
            throw new NoSuchElementException("Element not found...");
        }
        pointer = this.last;
    }


    /**
     * Добавление элемента после указателя
     *
     * @param valToAdd Элемент
     */
    public void addAfterPointer(T valToAdd) {
        if (this.pointer == null) addLast(valToAdd);
        else addByIndex(indexOf(pointer.prev.value), valToAdd);

    }

    /**
     * Добавление элемента до указателя
     *
     * @param valToAdd Элемент
     */
    public void addBeforePointer(T valToAdd) {
        if (this.pointer == null) addLast(valToAdd);
        else addByIndex(indexOf(pointer.prev.value), valToAdd);
    }

    /**
     * Перемещение указателя вправо
     */
    public void shiftPointerR() {
        pointer = this.pointer.next;
    }

    /**
     * Перемещение указателя влево
     */
    public void shiftPointerL() {
        pointer = this.pointer.prev;
    }

    /**
     * Замена двух соседних элементов у указателя
     */
    public void swapNearElems() {
        if (this.size > 2) {
            T temp = pointer.prev.value;
            pointer.prev.value = pointer.next.value;
            set(indexOf(pointer.next.value), temp);

        }
    }

    /**
     * Удаление элемента после указателя
     */
    public void delElemAfterPointer() {
        remove(pointer.next.value);
    }

    /**
     * Удаление элемента до указателя
     */
    public void delElemBeforePointer() {
        remove(pointer.prev.value);
    }

    /**
     * Узел связи между элементами в списке
     */
    private class Node {
        /**
         * Связь на следующий элемент
         */
        private Node next;
        /**
         * Связь на предыдущий элемент
         */
        private Node prev;
        /**
         * Элемент
         */
        private T value;

        /**
         * Конструктор связи с параметрами
         *
         * @param next  Связь на следующий элемент
         * @param prev  Связь на предыдущий элемент
         * @param value Элемент
         */
        Node(Node next, Node prev, T value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }

        /**
         * Конструктор без параметров
         *
         * @param value Элемент
         */
        Node(T value) {
            this(null, null, value);
        }
    }
}