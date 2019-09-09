package com.droidcon.conference

import android.content.Context
import androidx.fragment.app.Fragment
import com.droidcon.DependencyManager
import com.droidcon.android.AndroidApplication

class ConferenceFragment : Fragment() {

    private var dependencyManager: DependencyManager? = null

    private val conferenceViewModel: ConferenceViewModel by lazy {
        dependencyManager!!.conferenceViewModel
    }

    private val conferenceObserver: () -> Unit = {
        conferenceViewModel.name
        conferenceViewModel.loading
        conferenceViewModel.networkError
        conferenceViewModel.unknownError
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dependencyManager = (context.applicationContext as AndroidApplication).dependencyManager
    }

    override fun onResume() {
        super.onResume()
        conferenceViewModel.setObserver(conferenceObserver)
    }

    override fun onPause() {
        conferenceViewModel.removeObserver()
        super.onPause()
    }

    override fun onDetach() {
        dependencyManager = null
        super.onDetach()
    }
}
