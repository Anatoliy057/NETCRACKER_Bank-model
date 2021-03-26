package me.anatoliy57.bankmodel.util;


import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Util {

    public static int generateAverageInt(int average, int scatter, Random random) {
        return average + (random.nextInt(scatter) * (random.nextBoolean() ? 1 : -1));
    }
}
