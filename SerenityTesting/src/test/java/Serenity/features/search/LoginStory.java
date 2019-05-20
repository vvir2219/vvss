package Serenity.features.search;


import Serenity.steps.serenity.EndUserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class LoginStory {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps vasile;
    private String username = "unatoeh";
    private String password = "unatoehunatoeh";

    @Issue("#WIKI-2")
    @Test
    public void login_with_valid_data_should_go_through() {
        vasile.opens_login_page();
        vasile.enters_account(username, password);
        vasile.should_be_logged_in();
    }

    @Test
    public void login_with_invalid_data_shouldnt_work() {
        vasile.opens_login_page();
        vasile.enters_account("long username which does not exist i hope, but if it exists i'm interested who made this username", "anything");
        vasile.should_not_be_logged_in();
    }
}
