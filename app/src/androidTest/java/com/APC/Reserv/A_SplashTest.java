package com.APC.Reserv;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class A_SplashTest {

    @Rule
    public ActivityTestRule<Splash> splashatr = new ActivityTestRule<>(Splash.class);
    private Splash splash = null;

    private Instrumentation.ActivityMonitor monitorfirst = getInstrumentation().addMonitor(Firsttime.class.getName(),null,false);

    @Before
    public void setUp() throws Exception{
        splash= splashatr.getActivity();
    }

    @Test
    public void testLaunchFirst() throws Exception {
        View viewbtnreg = splash.findViewById(R.id.l1);
        assertNotNull(viewbtnreg);

        Activity activityfirst = getInstrumentation().waitForMonitorWithTimeout(monitorfirst, 5000);
        assertNotNull(activityfirst);
        activityfirst.finish();
    }

    @After
    public void tearDown() throws Exception {
        splash = null;
    }
}
