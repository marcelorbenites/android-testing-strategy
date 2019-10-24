package com.droidcon.conference

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.droidcon.DroidconApplication
import com.droidcon.FakeDispatcherFactory
import com.droidcon.TestActivity
import com.droidcon.TestApplication
import com.droidcon.testing.R
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestApplication::class)
class ConferenceComponentTest {

    @get:Rule
    val rule: ActivityTestRule<TestActivity> = ActivityTestRule(TestActivity::class.java)

    @Test
    fun `Given a registered conference When conference screen appears Then show conference name`() {
        val server = MockWebServer()
        server.start()
        val baseUrl = server.url("/").toString()

        val json = """
            [{
              "id": "1",
              "name": "Droidcon"
            }]
        """

        server.enqueue(MockResponse().setResponseCode(200).setBody(json))

        rule.activity.testDependencyManager = DroidconApplication.Builder()
            .registerConferenceGateway(lazy { OkHttpConferenceGateway(baseUrl, OkHttpClient()) })
            .registerDispatcherFactory(lazy { FakeDispatcherFactory() })
            .start()

        rule.activity.showFragment(ConferenceFragment())

        onView(withId(R.id.conferenceName)).check(matches(ViewMatchers.withText("Droidcon")))

        server.shutdown()
    }
}
