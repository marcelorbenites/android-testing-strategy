package com.droidcon.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droidcon.conference.ConferenceFragment
import com.droidcon.testing.R

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.activity_container,
                    ConferenceFragment()
                )
                .commit()
        }
    }
}
