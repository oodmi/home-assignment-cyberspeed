package com.oodmi.service;

import com.oodmi.model.InputParams;
import com.oodmi.model.Symbol;
import com.oodmi.model.WinCombination;

import java.util.List;
import java.util.Map;

public class RewardCalculation {
    public static Double calculateReward(Map<String, List<String>> winningCombinations, InputParams inputParams) {
        double reward = 0d;

        for (Map.Entry<String, List<String>> entry : winningCombinations.entrySet()) {
            String s = entry.getKey();
            List<String> combinations = entry.getValue();

            Symbol symbol = inputParams.config().symbols().get(s);

            for (String it : combinations) {
                WinCombination winCombination = inputParams.config().winCombinations().get(it);
                reward += winCombination.rewardMultiplier() * symbol.rewardMultiplier() * inputParams.bettingAmount();
            }
        }

        return reward;
    }
}
