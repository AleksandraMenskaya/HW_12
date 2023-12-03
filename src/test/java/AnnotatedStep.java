import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class AnnotatedStep {
    @Step("Открываем гланую страницу")
    public void openMainPage() {
        open("https://github.com");
    };
    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $("[data-target='qbsearch-input.inputButtonText']").click();
        $("[name='query-builder-test']").sendKeys(repo);
        $("[name='query-builder-test']").submit();
    }
    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepository(String repo) {
        $(linkText(repo)).click();
    }
    @Step("Открываем таб Issue")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }
    @Step("Проверяем наличие Issue с номером {issue}")
    public void shouldSeeIssueWithNumber(int issue) {
        $(withText("#" + issue)).shouldBe(Condition.exist);
    }
}


