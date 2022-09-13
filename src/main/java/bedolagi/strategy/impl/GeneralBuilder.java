package bedolagi.strategy.impl;

import bedolagi.strategy.service.HackathonService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Component
public class GeneralBuilder {
    private char[][][] result = new char[100][100][100];
    private List<String> words;

    private final HackathonService hackathonService;

    @PostConstruct
    @SneakyThrows
    public void after() {

        BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/trash/words_only.json"));

        words = fileReader
                .lines()
                .collect(Collectors.toList());

    }

    public enum VertDir {
        UP, DOWN
    }

    public enum HorDir {
        FORWARD, BACK, LEFT, RIGHT
    }

    public void build() {
    }

    public String getCandidate(char character) {
        return words.stream()
                .filter(word -> word.contains(String.valueOf(character)))
                .max(Comparator.comparing(String::length))
                .orElseThrow();
    }

    public void placeIn(String word, int x, int y, int z, HorDir horDir, VertDir vertDir) {
        int xOffset = result.length / 2;
        int yOffset = xOffset;

        for (char c : word.toCharArray()) {
            result[x + xOffset][y + yOffset][z] = c;


            if (horDir == null) {
                // NOP
            } else if (horDir == HorDir.FORWARD) {
                x++;
            } else if (horDir == HorDir.BACK) {
                x--;
            } else if (horDir == HorDir.LEFT) {
                y++;
            } else if (horDir == HorDir.RIGHT) {
                y--;
            }

            // z change
            if (vertDir == null) {
                // NOP
            } else if (vertDir == VertDir.UP) {
                z++;
            } else if (vertDir == VertDir.DOWN) {
                z--;
            }
        }
    }
}
