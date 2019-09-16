package com.APC.Reserv;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class C_LoginTest {

    @Rule
    public ActivityTestRule<Splash> mActivityTestRule = new ActivityTestRule<>(Splash.class);

    @Test
    public void loginTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.botoniniciar), withText("Comenzar"),
                        childAtPosition(
                                allOf(withId(R.id.layoutfirst),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttontoregister), withText("REGISTRARSE"),
                        childAtPosition(
                                allOf(withId(R.id.scrollconstraintlayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                4)));
        appCompatButton2.perform(closeSoftKeyboard(), scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button4), withText("INICIAR SESIÓN"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                7)));
        appCompatButton3.perform(closeSoftKeyboard(), scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.buttontoforget), withText("¿Olvidó su contraseña?"),
                        childAtPosition(
                                allOf(withId(R.id.scrollconstraintlayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                3)));
        appCompatButton4.perform(closeSoftKeyboard(), scrollTo(), click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.button5), withText("INICIAR SESIÓN"),
                        childAtPosition(
                                allOf(withId(R.id.scrollconstraintlayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                3)));
        appCompatButton5.perform(closeSoftKeyboard(), scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.correologin),
                        childAtPosition(
                                allOf(withId(R.id.scrollconstraintlayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("bbbbbb"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.passlogin),
                        childAtPosition(
                                allOf(withId(R.id.scrollconstraintlayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                1)));
        appCompatEditText2.perform(scrollTo(), replaceText("12345678"), closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.buttonlogin), withText("Iniciar Sesión"),
                        childAtPosition(
                                allOf(withId(R.id.scrollconstraintlayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                2)));
        appCompatButton6.perform(closeSoftKeyboard(), scrollTo(), click());

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
