package ro.upt.graph.util;

import java.util.Random;

public class Randomizer {
    public static int getRandomBetween(int min, int max) {
        return new Random().nextInt(min, max);
    }
}
