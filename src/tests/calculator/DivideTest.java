package calculator;

import helpers.Calculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@SuppressWarnings("FieldCanBeLocal")
public class DivideTest {

    @DataProvider(name = "PositiveScenariosData")
    public Object[][] getPositiveScenariosData() {
        return new Object[][] {
                {"40", "5", "8"},
                {"-15", "5", "-3"},
                {"0.1", "0.5", "0,2"},
                {"0", "1.00000001", "0"},
                {"-1.00000001", "-1", "1,00000001"},
                {"1", "-1", "-1"},
                {"1", "100000000", "0,00000001"}
        };
    }

    @Test(priority = 100, dataProvider = "PositiveScenariosData")
    public void verifyCorrectResultDivide(String firstNumber, String secondNumber, String expectedResult) throws IOException {
        // ARRANGE

        // ACT
        String result = Calculator.divide(firstNumber, secondNumber);

        // ASSERT
        assertThat(result).isEqualTo("Result: " + expectedResult);
    }

    @DataProvider(name = "NegativeScenariosData")
    public Object[][] getNegativeScenariosData() {
        return new Object[][] {
                {"8.", "2", "Invalid argument", "16"},
                {"a", "2", "Invalid argument", "2"},
                {".1", ".2", "Invalid argument", "0,5"}
        };
    }

    @Test(priority = 200, dataProvider = "NegativeScenariosData")
    public void verifyErrorMessagesDivide(String firstNumber, String secondNumber, String expectedOutput, String unexpectedOutput) throws IOException {
        // ARRANGE

        // ACT
        String result = Calculator.divide(firstNumber, secondNumber);

        // ASSERT
        assertThat(result)
                .contains(expectedOutput)
                .doesNotContain(unexpectedOutput);
    }


}
