package com.bombie.brawlwatch.brawlstarsapi.util.etc;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bombie.brawlwatch.brawlstarsapi.domain.database.battle.Result3v3;

@Service
public class TrophyChangeCalculator {

    List<Integer> boundaries = Arrays.asList(49, 99, 199, 299, 399, 499, 599, 699, 799, 899, 999, 1099, 1199);

    int[][] _3v3 = {
        {8, 0},
        {8, -1},
        {8, -2},
        {8, -3},
        {8, -4},
        {8, -5},
        {8, -6},
        {8, -7},
        {8, -8},
        {7, -9},
        {6, -10},
        {5, -11},
        {4, -12},
        {3, -12},
    };

    int[][] duo = {
        {9, 7, 4, 0, 0},
        {9, 7, 4, 0, -1},
        {9, 7, 3, -1, -2},
        {9, 7, 2, -2, -3},
        {9, 7, 1, -3, -4},
        {9, 7, 0, -4, -5},
        {9, 7, 0, -5, -6},
        {9, 7, 0, -6, -8},
        {9, 7, 0, -7, -9},
        {7, 6, -1, -8, -9},
        {7, 6, -3, -8, -10},
        {5, 4, -4, -9, -11},
        {5, 4, -6, -10, -12},
        {4, 2, -6, -10, -12},
    };

    int[][] solo = {
        {10, 8, 7, 6, 4, 2, 2, 1, 0, 0},
        {10, 8, 7, 6, 3, 2, 2, 0, -1, -2},
        {10, 8, 7, 6, 3, 1, 0, -1, -2, -2},
        {10, 8, 6, 5, 3, 1, 0, -2, -3, -3},
        {10, 8, 6, 5, 2, 0, 0, -3, -4, -4},
        {10, 8, 6, 5, 2, -1, -2, -3, -5, -5},
        {10, 8, 6, 4, 2, -1, -2, -5, -6, -6},
        {10, 8, 6, 4, 1, -2, -2, -5, -7, -8},
        {10, 8, 6, 4, 1, -3, -4, -5, -8, -9},
        {9, 7, 5, 2, 0, -3, -4, -7, -9, -10},
        {8, 6, 4, 1, -1, -3, -6, -8, -10, -11},
        {6, 5, 3, 1, -2, -5, -6, -9, -11, -12},
        {5, 4, 1, 0, -2, -6, -7, -10, -12, -13},
        {5, 3, 0, -1, -2, -6, -8, -11, -12, -13},
    };

    public int get3v3(Result3v3 result, int trophyCount) {
        int change = 0;
        if (result == Result3v3.VICTORY) {
            change = _3v3[getTierIndex(trophyCount)][0];
        }
        if (result == Result3v3.DEFEAT) {
            change = _3v3[getTierIndex(trophyCount)][1];
        }
        return change;
    }

    public int getDuo(int rank, int trophyCount) {
        return duo[getTierIndex(trophyCount)][rank - 1];
    }

    public int getSolo(int rank, int trophyCount) {
        return solo[getTierIndex(trophyCount)][rank - 1];
    }

    private int getTierIndex(int trophyCount) {
        return (int) boundaries.stream()
                .filter(tierCeiling -> trophyCount > tierCeiling)
                .count();
    }
}
