package com.management.rewards.util;

public class TransactionDataUtil {

    /** calculate rewards as per amount */
    public static int calculateRewards(double amount) {
        if (amount <= 100 && amount >= 50) {
            return ((int) (amount - 50));
        } else if (amount > 100) {
            return ((int) (amount - 100) * 2 + 50);
        }

        return 0;
    }
}
