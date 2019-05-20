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
public class SearchCategoryStory {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps vasile;
    private String category = "Category:Money";

    @Issue("#WIKI-3")
    @Test
    public void searching_existing_category_should_display_tree() {
        vasile.opens_category_tree_page();
        vasile.searches_category(category);
        vasile.gets_results(); // all about the gainZZ
    }

    @Test
    public void searching_inexisting_category_should_display_message() {
        vasile.opens_category_tree_page();
        vasile.searches_category("untoeahntuhntuoaehoe");
        vasile.finds_no_results();
    }
}
