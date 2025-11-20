package my.important.job;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyTest {

    @Test
    @Story("test")
    @Feature("sum")
    @Description("Сравнение чисел")
    void test() {
        Assertions.assertEquals(1, 1);
    }
}
