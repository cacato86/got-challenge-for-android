package es.npatarino.android.gotchallenge;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class CharacterDetailTest {

    private String CHARACTER_DESCRIPTION = "In A Game of Thrones (1996), Tyrion is introduced as the third and youngest child of wealthy and powerful Tywin Lannister, the former Hand of the King. Tyrion's elder sister Cersei is the Queen of Westeros by virtue of her marriage to King Robert Baratheon, and Cersei's male twin Jaime is one of the Kingsguard, the royal security detail. Described as an ugly ('for all the world like a gargoyle'), malformed dwarf with mismatched green and black eyes, Tyrion possesses the pale blond hair of a Lannister but has a complicated relationship with the rest of them.";

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule(HomeActivity.class);

    @Test
    public void goToCharacterDetailActivityAndCheckSearchIconNotFound() {
        onView(withId(R.id.rv_character))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.ic_search)).check(doesNotExist());
    }

    @Test
    public void goToCharacterDetailActivityAndCheckDetailCharacter() {
        onView(withId(R.id.rv_character))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.tv_description), withText(CHARACTER_DESCRIPTION))).check(matches(isDisplayed()));
    }

}