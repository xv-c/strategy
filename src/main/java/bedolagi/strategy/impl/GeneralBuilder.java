package bedolagi.strategy.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

@Data
@RequiredArgsConstructor
public class GeneralBuilder {
    private char[][][] result = new char[10][10][10];

    private final List<String> words;

    public enum VertDir {
        UP, DOWN
    }

    public enum HorDir {
        FORWARD, BACK, LEFT, RIGHT
    }

    public void build() {
        //String longest = words
        //        .stream()
        //        .filter(x->x.length() ==5)
        //        .findFirst()
        //        .get();
//
        //placeIn(longest, 0, 0, 0, HorDir.FORWARD, null);
    }

    public String getCandidate(char character) {
        return "";
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
