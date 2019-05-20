package Serenity.steps.serenity;

import Serenity.pages.CategoryTreePage;
import Serenity.pages.DictionaryPage;
import Serenity.pages.LoginPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    private DictionaryPage dictionaryPage;
    private LoginPage loginPage;
    private CategoryTreePage categoryTreePage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void should_see_definition(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void should_not_find_page() {
        assertThat("Page does not exist", dictionaryPage.pageDoesNotExist());
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }

    // testing login

    @Step
    public void opens_login_page() {
        loginPage.open();
    }

    @Step
    public void enters_account(String username, String password){
        loginPage.type_username(username);
        loginPage.type_password(password);
        loginPage.login();
    }

    @Step
    public void should_be_logged_in() {
        assertThat("User is logged in", loginPage.isLoggedIn());
    }

    @Step
    public void should_not_be_logged_in() {
        assertThat("User is not logged in.", !loginPage.isLoggedIn());
    }

    // testing show category tree

    @Step
    public void opens_category_tree_page () { categoryTreePage.open(); }

    @Step
    public void searches_category(String category) {
        categoryTreePage.type_category(category);
        categoryTreePage.showTree();
    }

    @Step
    public void gets_results() {
        assertThat("Category has results", categoryTreePage.hasResults());
    }

    @Step
    public void finds_no_results() {
        assertThat("No category", !categoryTreePage.hasResults());
    }
}