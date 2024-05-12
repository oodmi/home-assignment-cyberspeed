package com.oodmi.service;

import com.oodmi.enums.Type;
import com.oodmi.model.InputParams;
import com.oodmi.model.Symbol;
import com.oodmi.model.WinCombination;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RewardCalculation {

    public static Double calculateReward(Map<String, List<String>> winningCombinations, InputParams inputParams) {
        double reward = 0d;

        for (Map.Entry<String, List<String>> entry : winningCombinations.entrySet()) {
            String s = entry.getKey();
            List<String> combinations = entry.getValue();

            Symbol symbol = inputParams.config().symbols().get(s);

            double symbolReward = inputParams.bettingAmount() * symbol.rewardMultiplier();

            for (String it : combinations) {
                WinCombination winCombination = inputParams.config().winCombinations().get(it);
                symbolReward *= winCombination.rewardMultiplier();
            }

            reward += symbolReward;
        }

        return reward;
    }

    public static Map.Entry<Double, String> applyBonus(Map<String, List<String>> winningCombinations,
                                                       List<List<String>> matrix,
                                                       InputParams inputParams,
                                                       Double reward) {
        if (winningCombinations.isEmpty()) {
            return Map.entry(reward, "");
        } else {
            List<String> allCells = matrix.stream().flatMap(Collection::stream).toList();

            for (String k : inputParams.config().symbols().keySet()) {
                Symbol symbol = inputParams.config().symbols().get(k);
                if (symbol.type().equals(Type.BONUS)) {
                    if (allCells.contains(k)) {
                        switch (symbol.impact()) {
                            case EXTRA_BONUS:
                                reward += symbol.extra();
                                break;
                            case MULTIPLY_REWARD:
                                reward *= symbol.rewardMultiplier();
                                break;
                            case MISS:
                                break;
                        }
                        return Map.entry(reward, k);
                    }
                }
            }
        }

        return Map.entry(reward, "");
    }
}
