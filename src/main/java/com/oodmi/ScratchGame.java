package com.oodmi;

import com.oodmi.configuration.JsonObjectMapper;
import com.oodmi.model.MatrixResult;
import com.oodmi.service.InputParser;
import com.oodmi.service.MatrixGenerator;
import com.oodmi.service.RewardCalculation;
import com.oodmi.service.WinChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ScratchGame {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScratchGame.class);

    public static void main(String[] args) {
        var inputParams = InputParser.parse(args);
        var matrix = MatrixGenerator.generateMatrix(inputParams.config());
        var winningCombinations = WinChecker.checkWinningCombinations(matrix, inputParams.config());
        var reward = RewardCalculation.calculateReward(winningCombinations, inputParams);

        var matrixResult = new MatrixResult(matrix, reward.intValue(), winningCombinations, "");

        LOGGER.info(JsonObjectMapper.getInstance().asString(matrixResult));
    }
}