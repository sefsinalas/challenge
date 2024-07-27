package calculator;

import helpers.Calculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@SuppressWarnings("FieldCanBeLocal")
public class AddTest {

    @DataProvider(name = "PositiveScenariosData")
    public Object[][] getPositiveScenariosData() {
        return new Object[][] {
                {"8", "5.9", "13,9"},
                {"-3", "5", "2"},
                {"0.1", "0.2", "0,3"},
                {"0", "0", "0"},
                {"1.00000001", "1.00000001", "2,00000002"}
        };
    }

    @Test(priority = 100, dataProvider = "PositiveScenariosData")
    public void verifyCorrectResultAdd(String firstNumber, String secondNumber, String expectedResult) throws IOException {
        // ARRANGE

        // ACT
        String result = Calculator.add(firstNumber, secondNumber);

        // ASSERT
        assertThat(result).isEqualTo("Result: " + expectedResult);
    }

    @DataProvider(name = "NegativeScenariosData")
    public Object[][] getNegativeScenariosData() {
        return new Object[][] {
                {"8.", "2", "Invalid argument", "10"},
                {"a", "2", "Invalid argument", "2"},
                {".1", ".2", "Invalid argument", ".3"},
                {"\1", "\1", "Invalid argument", "2"}
        };
    }

    @Test(priority = 200, dataProvider = "NegativeScenariosData")
    public void verifyErrorMessagesAdd(String firstNumber, String secondNumber, String expectedOutput, String unexpectedOutput) throws IOException {
        // ARRANGE

        // ACT
        String result = Calculator.add(firstNumber, secondNumber);

        // ASSERT
        assertThat(result)
                .contains(expectedOutput)
                .doesNotContain(unexpectedOutput);
    }


}
