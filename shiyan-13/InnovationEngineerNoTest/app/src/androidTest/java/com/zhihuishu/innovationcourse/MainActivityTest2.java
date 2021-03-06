package com.zhihuishu.innovationcourse;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.teacher_name_textView), withText("张海霞"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.teacher_listView),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("张海霞")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.teacher_name_textView), withText("陈江"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.teacher_listView),
                                        1),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("陈江")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.teacher_name_textView), withText("叶蔚"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.teacher_listView),
                                        2),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("叶蔚")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.teacher_name_textView), withText("叶蔚"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.teacher_listView),
                                        2),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("叶蔚")));

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.teacher_listView),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(0);
        linearLayout.perform(click());

        pressBack();

        DataInteraction linearLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.teacher_listView),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(1);
        linearLayout2.perform(click());

        pressBack();

        DataInteraction linearLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.teacher_listView),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(2);
        linearLayout3.perform(click());

        pressBack();
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
