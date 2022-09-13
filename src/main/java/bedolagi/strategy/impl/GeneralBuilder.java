package bedolagi.strategy.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

@Data
@RequiredArgsConstructor
public class GeneralBuilder {
    private char[][][] result = new char[100][100][100];

    private final List<String> words;

    public enum VertDir {
        UP, DOWN
    }

    public enum HorDir {
        FORWARD, BACK, LEFT, RIGHT
    }

    public void build() {
    }

    public void placeIn(String word, int x, int y, int z, HorDir horDir, VertDir vertDir) {
        int xOffset = 50;
        int yOffset = 50;

        for (char c : word.toCharArray()) {
            result[x + xOffset][y + yOffset][z] = c;

            // x change
            if (horDir == HorDir.FORWARD) {
                x++;
            }
            if (horDir == HorDir.BACK) {
                x--;
            }

            // y change
            if (horDir == HorDir.LEFT) {
                y++;
            }
            if (horDir == HorDir.RIGHT) {
                y--;
            }

            // z change
            if (vertDir == VertDir.UP) {
                z++;
            }
            if (vertDir == VertDir.DOWN) {
                z--;
            }
        }
    }
}
