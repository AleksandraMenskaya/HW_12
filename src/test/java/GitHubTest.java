import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;

import io.qameta.allure.selenide.AllureSelenide;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class GitHubTest {
    private static final String REPOSITORY = "AleksandraMenskaya/HW_2";
    private static final int ISSUE = 10;
    @Test
    @DisplayName("Selenide Test with listener")
    @Owner("Aleksandra Menskaya")
    @Story("Проверка Issue ")
    public void selenideIssueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("[name='query-builder-test']").sendKeys(REPOSITORY);
        $("[name='query-builder-test']").submit();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText("#" + ISSUE)).shouldBe(Condition.exist);
    }

    @Test
    @DisplayName("Step Test")
    @Owner("Aleksandra Menskaya")
    @Story("Проверка Issue ")
    public void lambdaIssueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем гланую страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("[name='query-builder-test']").sendKeys(REPOSITORY);
        $("[name='query-builder-test']").submit();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
        $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issue", () -> {
        $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером" + ISSUE, () -> {
        $(withText("#" + ISSUE)).shouldBe(Condition.exist);
        });
    }
    @Test
    @DisplayName("Annotated Test")
    @Owner("Aleksandra Menskaya")
    @Story("Проверка Issue ")
    public void annotatedStepIssueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        AnnotatedStep steps = new  AnnotatedStep();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepository(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
