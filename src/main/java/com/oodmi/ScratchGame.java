package com.oodmi;

import com.oodmi.configuration.JsonObjectMapper;
import com.oodmi.service.InputParser;
import com.oodmi.service.MatrixGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ScratchGame {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScratchGame.class);

    public static void main(String[] args) {
        var inputParams = InputParser.parse(args);
        var matrixResult = MatrixGenerator.generateMatrix(inputParams.config());

        LOGGER.info("{}", JsonObjectMapper.getInstance().asString(matrixResult));
    }
}