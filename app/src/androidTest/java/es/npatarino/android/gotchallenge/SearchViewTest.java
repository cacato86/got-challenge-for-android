package es.npatarino.android.gotchallenge;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TableLayout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.npatarino.android.gotchallenge.Fragments.GoTCharactersListFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class SearchViewTest {
    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule(HomeActivity.class);

    @Test
    public void isSearchMenuItemVisibleInCharacterFragment() {
        onView(withId(R.id.ic_search)).check(matches(isDisplayed()));
    }

    @Test
    public void isSearchMenuItemVisibleInHouseFragment() {
        onView(withText("Houses")).perform(click());
        onView(withId(R.id.ic_search)).check(doesNotExist());
    }
}