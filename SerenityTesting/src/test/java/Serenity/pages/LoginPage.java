package Serenity.pages;


import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl("https://en.wiktionary.org/w/index.php?title=Special:UserLogin&returnto=log+in")
public class LoginPage extends PageObject {
    @FindBy(id = "wpName1")
    private WebElementFacade inputUsername;
    @FindBy(id = "wpPassword1")
    private WebElementFacade inputPassword;
    @FindBy(id = "wpLoginAttempt")
    private WebElementFacade loginButton;

    public boolean isLoggedIn() {
        WebElementFacade personalInformation = find(By.id("p-personal"));
        return personalInformation.containsElements(By.id("pt-userpage"));
    }

    public void type_username(String username) {
        inputUsername.type(username);
    }

    public void type_password(String password) {
        inputPassword.type(password);
    }

    public void login() {
        loginButton.click();
    }
}
