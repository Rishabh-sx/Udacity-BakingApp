package com.rishabh.bakingapp.onboard;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.rishabh.bakingapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {

    @Rule
    public ActivityTestRule<OnBoardActivity> mActivityTestRule = new ActivityTestRule<>(OnBoardActivity.class);

    @Test
    public void onBoardActivityTest3() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_recipe), withText("Ingredients"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Ingredients")));

        ViewInteraction textView1 = onView(
                allOf(withId(R.id.tv_recipe), withText("Step 1\nRecipe Introduction"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv),
                                        1),
                                0),
                        isDisplayed()));
        textView1.check(matches(withText("Step 1 Recipe Introduction")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tv_recipe), withText("Step 7\nFinishing Steps"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.rv),
                                        7),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Step 7 Finishing Steps")));


    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
