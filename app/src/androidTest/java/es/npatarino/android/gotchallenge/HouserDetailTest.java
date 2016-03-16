package es.npatarino.android.gotchallenge;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class HouserDetailTest {

    private String CHARACTER_NAME = "Tyrion Lannister";

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule(HomeActivity.class);

    @Before
    public void setUp(){
        onView(withText("Houses")).perform(click());
    }

    @Test
    public void goToCharacterDetailActivityAndCheckSearchIcon() {
        onView(allOf(withId(R.id.rv_house),withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.ic_search)).check(matches(isDisplayed()));
    }

    @Test
    public void goToCharacterDetailActivityAndCheckDetailCharacter() {
        onView(allOf(withId(R.id.rv_house),withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withText(CHARACTER_NAME)).check(matches(isDisplayed()));
    }

}