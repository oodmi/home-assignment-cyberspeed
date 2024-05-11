package com.oodmi;

import com.oodmi.configuration.JsonObjectMapper;
import com.oodmi.service.InputParser;
import com.oodmi.service.MatrixGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var inputParser = new InputParser();
        var matrixGenerator = new MatrixGenerator();

        var inputParams = inputParser.parse(args);
        var matrixResult = matrixGenerator.generateMatrix(inputParams);

        LOGGER.info("{}", JsonObjectMapper.getInstance().asString(matrixResult));
    }
}