package com.droidcon.conference

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.droidcon.android.MainActivity
import com.droidcon.testing.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConferenceEndToEndTest {

    @get:Rule
    val rule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun givenRegisteredConferenceWhenApplicationStartsStartShowConferenceName() {

        rule.launchActivity(Intent())

        Thread.sleep(2000)

        onView(ViewMatchers.withId(R.id.conferenceName)).check(matches(withText("Droidcon")))
    }
}
