package bedolagi.strategy.impl;

import bedolagi.strategy.domain.TowerCDTO;
import bedolagi.strategy.feign.TowerFeignClient;
import bedolagi.strategy.service.HackathonService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static bedolagi.strategy.service.HackathonService.generateTowerId;
import static bedolagi.strategy.service.HackathonService.towerToLetter;

@Data
@RequiredArgsConstructor
@Component
public class GeneralBuilder {
    @Value("${hackathon.token}")
    private String token;

    private char[][][] result = new char[100][100][100];
    private List<String> words;

    private final HackathonService hackathonService;
    private final TowerFeignClient feignClient;

    @PostConstruct
    @SneakyThrows
    public void after() {
        BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/trash/words_only.json"));

        words = fileReader
                .lines()
                .collect(Collectors.toList());

        build();

        feignClient.sendTower(token, TowerCDTO.builder()
                .towerId(generateTowerId())
                .letters(towerToLetter(result))
                .build());
    }

    public enum VertDir {
        UP, DOWN
    }

    public enum HorDir {
        FORWARD, BACK, LEFT, RIGHT
    }

    public void build() {
        String s = words.stream()
                .max(Comparator.comparing(String::length))
                .orElseThrow();

        placeIn(s, 0, 0, 0, null, VertDir.UP);
    }

    public String getCandidate(char character) {
        return words.stream()
                .filter(word -> word.contains(String.valueOf(character)))
                .max(Comparator.comparing(String::length))
                .orElseThrow();
    }

    public String getCandidateByLast(String word) {
        return words.stream()
                .filter(l -> l.startsWith(String.valueOf(word.charAt(word.length() - 1))))
                .max(Comparator.comparing(String::length))
                .orElse(null);
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
