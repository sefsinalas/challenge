package calculator;

import helpers.Calculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@SuppressWarnings("FieldCanBeLocal")
public class MultiplyTest {

    @DataProvider(name = "PositiveScenariosData")
    public Object[][] getPositiveScenariosData() {
        return new Object[][] {
                {"8", "5", "40"},
                {"-3", "5", "-15"},
                {"0.2", "0.5", "0,1"},
                {"0", "0", "0"},
                {"1.00000001", "0", "0"},
                {"1.00000001", "-1", "-1,00000001"},
                {"-1", "-1", "1"},
                {"0.00000001", "100000000", "1"},
                {"1e-10", "1e10", "1"}
        };
    }

    @Test(priority = 100, dataProvider = "PositiveScenariosData")
    public void verifyCorrectResultMultiply(String firstNumber, String secondNumber, String expectedResult) throws IOException {
        // ARRANGE

        // ACT
        String result = Calculator.multiply(firstNumber, secondNumber);

        // ASSERT
        assertThat(result).isEqualTo("Result: " + expectedResult);
    }

    @DataProvider(name = "NegativeScenariosData")
    public Object[][] getNegativeScenariosData() {
        return new Object[][] {
                {"8.", "2", "Invalid argument", "16"},
                {"a", "2", "Invalid argument", "2"},
                {".1", ".2", "Invalid argument", ".3"}
        };
    }

    @Test(priority = 200, dataProvider = "NegativeScenariosData")
    public void verifyErrorMessagesMultiply(String firstNumber, String secondNumber, String expectedOutput, String unexpectedOutput) throws IOException {
        // ARRANGE

        // ACT
        String result = Calculator.multiply(firstNumber, secondNumber);

        // ASSERT
        assertThat(result)
                .contains(expectedOutput)
                .doesNotContain(unexpectedOutput);
    }


}
