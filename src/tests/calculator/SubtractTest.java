package calculator;

import helpers.Calculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@SuppressWarnings("FieldCanBeLocal")
public class SubtractTest {

    @DataProvider(name = "PositiveScenariosData")
    public Object[][] getPositiveScenariosData() {
        return new Object[][] {
                {"8", "5", "3"},
                {"-3", "-5", "2"},
                {"0.3", "0.21", "0,09"},
                {"0", "0", "0"},
                {"1.00000001", "1.00000001", "0"},
                {"1.00000001", "1.00000002", "-0,00000001"},
                {"1e10", "1e10", "0"}
        };
    }

    @Test(priority = 100, dataProvider = "PositiveScenariosData")
    public void verifyCorrectResultSubtract(String firstNumber, String secondNumber, String expectedResult) throws IOException {
        // ARRANGE

        // ACT
        String result = Calculator.subtract(firstNumber, secondNumber);

        // ASSERT
        assertThat(result).isEqualTo("Result: " + expectedResult);
    }

    @DataProvider(name = "NegativeScenariosData")
    public Object[][] getNegativeScenariosData() {
        return new Object[][] {
                {"8.", "2", "Invalid argument", "6"},
                {"a", "2", "Invalid argument", "2"},
                {".1", ".2", "Invalid argument", "0,02"}
        };
    }

    @Test(priority = 200, dataProvider = "NegativeScenariosData")
    public void verifyErrorMessagesSubtract(String firstNumber, String secondNumber, String expectedOutput, String unexpectedOutput) throws IOException {
        // ARRANGE

        // ACT
        String result = Calculator.subtract(firstNumber, secondNumber);

        // ASSERT
        assertThat(result)
                .contains(expectedOutput)
                .doesNotContain(unexpectedOutput);
    }


}
