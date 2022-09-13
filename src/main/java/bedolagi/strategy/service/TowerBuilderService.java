package bedolagi.strategy.service;

import bedolagi.strategy.domain.TowerCDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TowerBuilderService {

    public static final int WORD_SIZE = 8;
    private final DictionaryService dictionaryService;

    TowerCDTO buildTower(String towerId) {
        char[][] field = build2dTower();

        return new TowerCDTO();
    }

    char[][] build2dTower() {
        Set<String> dictionary = dictionaryService.getDictionary();
        dictionary = dictionary.stream()
                .filter(s -> !s.endsWith(String.valueOf('ь')))
                .filter(s -> !s.endsWith(String.valueOf('ы')))
                .filter(s -> !s.endsWith(String.valueOf('ъ')))
                .collect(Collectors.toSet());
        char[][] field = new char[100][100];

        buildLayer(field, dictionary, 0, 8 * 1 - 1, 6);
        buildLayer(field, dictionary, 3, 8 * 2 - 1, 5);
        buildLayer(field, dictionary, 5, 8 * 3 - 1, 4);


        return field;
    }

    private void buildLayer(char[][] field, Set<String> dictionary, int x, int z, int countBlocks) {
        for (int i = 0; i < countBlocks; i++) {
            buildBlock(field, dictionary, x + WORD_SIZE * i, z);
        }
    }

    private void buildBlock(char[][] field, Set<String> dictionary, int x, int z) {

        char prefix1 = field[x][z];
        char postfix1 = field[x][z - WORD_SIZE + 1];

        String word1 = findWordWithSizeAndStartWithAndEndWith(WORD_SIZE, prefix1, postfix1, dictionary);
        writeInFieldVertical(field, word1, x, z);

        char prefix2 = field[x][z];
        char postfix2 = field[x][z - WORD_SIZE + 1];

        String word2 = findWordWithSizeAndStartWithAndEndWith(WORD_SIZE, prefix2, postfix2, dictionary);
        writeInFieldHorizontal(field, word2, x, z);

        char prefix3 = field[x + WORD_SIZE - 1][z];
        char postfix3 = field[x + WORD_SIZE - 1][z - WORD_SIZE + 1];

        String word3 = findWordWithSizeAndStartWithAndEndWith(WORD_SIZE, prefix3, postfix3, dictionary);
        writeInFieldVertical(field, word3, x + WORD_SIZE - 1, z);
    }

    private void writeInFieldHorizontal(char[][] field, String word, int x, int z) {
        for (int i = 0; i < word.length(); i++) {
            field[x + i][z] = word.charAt(i);
        }
    }

    private void writeInFieldVertical(char[][] field, String word, int x, int z) {
        for (int i = 0; i < word.length(); i++) {
            field[x][z - i] = word.charAt(i);
        }
    }

    String findWordWithSizeAndStartWithAndEndWith(int size, char prefix, char postfix, Set<String> dictionary) {
        Optional<String> wordOpt = dictionary.stream().filter(s -> s.length() == size)
                .filter(s -> prefix == '\u0000' || s.startsWith(String.valueOf(prefix)))
                .filter(s -> postfix == '\u0000' || s.endsWith(String.valueOf(postfix)))
                .findFirst();
        String word = wordOpt.orElse(null);

        if (word != null) {
            dictionary.remove(word);
        }

        return word;
    }

    String findWordWithSizeAndStartWith(int size, char prefix, Set<String> dictionary) {
        Optional<String> wordOpt = dictionary.stream().filter(s -> s.length() == size && s.startsWith(String.valueOf(prefix))).findFirst();
        String word = wordOpt.orElse(null);

        if (word != null) {
            dictionary.remove(word);
        }

        return word;
    }

    String findWordWithSize(int size, Set<String> dictionary) {
        Optional<String> wordOpt = dictionary.stream().filter(s -> s.length() == size).findFirst();
        String word = wordOpt.orElse(null);

        if (word != null) {
            dictionary.remove(word);
        }

        return word;
    }


}