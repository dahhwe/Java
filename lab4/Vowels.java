import java.util.Set;

/**
 * Класс для проверки гласных букв.
 */
public class Vowels implements LettersCounter {

    /**
     * @param str Строка для проверки.
     * @return Количество согласных букв в строке.
     */
    @Override
    public int analyse(String str) {

        LettersCounter vowels = (userStr) -> {
            Set<String> vowelsSet = Set.of("a", "e", "i", "o", "u", "y");
            int vowelsCount = 0;

            for (int i = 0; i < str.length(); ++i) {
                char letter = str.charAt(i);
                if (vowelsSet.contains(String.valueOf(letter))) {
                    ++vowelsCount;
                }
            }
            return vowelsCount;
        };
        return vowels.analyse(str);
    }
}