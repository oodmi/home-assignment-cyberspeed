package com.oodmi.service;

import com.oodmi.configuration.JsonObjectMapper;
import com.oodmi.model.Configuration;
import com.oodmi.model.InputParams;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class InputParser {

    private static final String CONFIG_KEY = "--config";
    private static final String BETTING_AMOUNT_KEY = "--betting-amount";

    public static InputParams parse(String[] args) {

        if (args.length != 4) {
            throw new IllegalArgumentException("Please use the following arguments: --config and --betting-amount ");
        }

        Map<String, String> argsMap = Map.of(
                args[0], args[1],
                args[2], args[3]
        );

        if (!argsMap.containsKey(CONFIG_KEY) || !argsMap.containsKey(BETTING_AMOUNT_KEY)) {
            throw new IllegalArgumentException("Please use the following arguments: --config and --betting-amount ");
        }

        Configuration config = parseConfig(argsMap.get(CONFIG_KEY));
        Integer bettingAmount = parseBettingAmount(argsMap.get(BETTING_AMOUNT_KEY));

        return new InputParams(config, bettingAmount);
    }

    public static Configuration parseConfig(String stringPath) {
        try {
            Path path = Paths.get(stringPath);
            String content = String.join("\n", Files.readAllLines(path));
            return JsonObjectMapper.getInstance().toObject(content, Configuration.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse file: " + stringPath, e);
        }
    }

    private static Integer parseBettingAmount(String bettingAmount) {
        try {
            return Integer.valueOf(bettingAmount);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot parse bettingAmountKey: " + bettingAmount, e);
        }
    }
}
