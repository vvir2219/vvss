package Serenity.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl("https://en.wiktionary.org/wiki/Special:CategoryTree")
public class CategoryTreePage extends PageObject {
    @FindBy(id = "ooui-php-1")
    private WebElementFacade inputCategory;

    @FindBy(xpath = "//button[@value='Show tree']")
    private WebElementFacade showTreeButton;

    public void type_category(String category) {
        inputCategory.type(category);
    }

    public void showTree() {
        showTreeButton.click();
    }

    public boolean hasResults() {
        return !findAll(By.className("CategoryTreeResult")).isEmpty();
    }
}
