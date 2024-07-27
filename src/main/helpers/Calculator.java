package helpers;

import helpers.logger.LoggerHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.nio.charset.StandardCharsets.UTF_8;

public abstract class Calculator {


    public static String calculate(String operation, String firstNumber, String secondNumber) throws IOException {
        Process p = Runtime.getRuntime().exec("java -jar src/main/resources/your-calculator.jar " + operation + " " + firstNumber + " " + secondNumber);
        InputStream inputStream = p.getInputStream();

        StringBuilder outputLines = new StringBuilder();
        String output;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LoggerHelper.logInfo("Command execution output: " + line);
                outputLines.append(line).append("\n");
            }
        } finally {
            output = outputLines.toString().trim();
        }

        return output;
    }

    /***
     * Add two numbers
     * @return [String]
     */
    public static String add(String firstNumber, String secondNumber) throws IOException {
        return calculate("add", firstNumber, secondNumber) ;
    }

    /***
     * Subctract two numbers
     * @return [String]
     */
    public static String subtract(String firstNumber, String secondNumber) throws IOException {
        return calculate("subtract", firstNumber, secondNumber) ;
    }

    /***
     * Multiply two numbers
     * @return [String]
     */
    public static String multiply(String firstNumber, String secondNumber) throws IOException {
        return calculate("multiply", firstNumber, secondNumber) ;
    }

    /***
     * Divide two numbers
     * @return [String]
     */
    public static String divide(String firstNumber, String secondNumber) throws IOException {
        return calculate("divide", firstNumber, secondNumber) ;
    }
}
