import java.util.Set;

/**
 * Класс для проверки согласных букв.
 */
public class Consonants implements LettersCounter {

    /***
     *
     * @param str Строка для проверки.
     * @return Количество согласных букв.
     */
    @Override
    public int analyse(String str) {

        LettersCounter consonants = (userStr) -> {
            Set<String> vowelsSet = Set.of("a", "e", "i", "o", "u", "y");
            int consonantsCount = 0;

            for (int i = 0; i < str.length(); ++i) {
                char letter = str.charAt(i);

                if (!(vowelsSet.contains(String.valueOf(letter)))
                        && (letter >= 'a' && letter <= 'z')) {
                    ++consonantsCount;
                }
            }
            return consonantsCount;
        };
        return consonants.analyse(str);
    }
}
