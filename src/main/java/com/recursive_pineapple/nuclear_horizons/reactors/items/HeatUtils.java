package com.recursive_pineapple.nuclear_horizons.reactors.items;

public class HeatUtils {

    private HeatUtils() {}

    public static int getTransferAmount(int sourceHeat, int destHeat, int max) {
        // positive = source > dest: pull, negative = source < dest: push
        int delta = sourceHeat - destHeat;

        int signum = delta < 0 ? -1 : 1;
        delta *= signum;

        if (delta > max) {
            return signum * max;
        } else {
            int balance = delta / 2;
            balance += delta % 2; // bias towards the dest
            return signum * balance;
        }
    }

    public static int getConsumableHeat(int maxHeat, int currentHeat, int addedHeat) {
        // if we have 50 heat and we subtract 25, we should be able to consume 25 heat
        // 50 stored - 75 removed = 50 removed
        // 25 out of 50 + 50 added = 25 added

        if (addedHeat > 0) {
            int remaining = maxHeat - currentHeat;

            return Math.min(addedHeat, remaining);
        } else {
            int remaining = currentHeat;

            return -Math.min(-addedHeat, remaining);
        }
    }
}
