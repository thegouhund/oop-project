package com.project.utils;

import java.util.random.RandomGenerator;

public class RandomUtils {
    public static RandomGenerator random() {
        return RandomGenerator.of("L128X256MixRandom");
    }
}
