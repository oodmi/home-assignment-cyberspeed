package com.oodmi;

import com.oodmi.configuration.JsonObjectMapper;
import com.oodmi.model.InputParams;
import com.oodmi.model.MatrixResult;
import com.oodmi.service.InputParser;
import com.oodmi.service.MatrixGenerator;
import com.oodmi.service.RewardCalculation;
import com.oodmi.service.WinChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


public class ScratchGame {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScratchGame.class);

    public static void main(String[] args) {
        InputParams inputParams = InputParser.parse(args);
        List<List<String>> matrix = MatrixGenerator.generateMatrix(inputParams.config());
        Map<String, List<String>> winningCombinations = WinChecker.checkWinningCombinations(matrix, inputParams.config());
        Double reward = RewardCalculation.calculateReward(winningCombinations, inputParams);
        Map.Entry<Double, String> rewardWithBonus = RewardCalculation.applyBonus(winningCombinations, matrix, inputParams, reward);

        MatrixResult matrixResult = new MatrixResult(matrix,
                rewardWithBonus.getKey().intValue(),
                winningCombinations,
                rewardWithBonus.getValue());

        LOGGER.info(JsonObjectMapper.getInstance().asString(matrixResult));
    }
}