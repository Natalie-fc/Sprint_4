package practicum.tests;

import practicum.pageobjects.MainPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FaqTest extends BaseTest {

    private final int questionIndex;

    public FaqTest(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    @Parameterized.Parameters(name = "FAQ Вопрос №{0}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}
        });
    }

    @Test
    public void faqAnswerShouldBeVisibleAfterClick() {
        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToFaqQuestion(questionIndex);
        mainPage.clickFaqQuestions(questionIndex);
        assertTrue("Ответ на вопрос №" + questionIndex + " не отображается", mainPage.isFaqAnswerVisible(questionIndex));
    }
}