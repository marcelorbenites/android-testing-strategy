package com.droidcon.conference

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.droidcon.DependencyManager
import com.droidcon.android.ViewContainer
import com.droidcon.testing.R
import kotlinx.android.synthetic.main.fragment_conference.*

class ConferenceFragment : Fragment() {

    private var dependencyManager: DependencyManager? = null

    private val conferenceViewModel by lazy {
        dependencyManager!!.conferenceViewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dependencyManager = (context as ViewContainer).dependencyManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_conference, container, false)
    }

    override fun onResume() {
        super.onResume()
        conferenceViewModel.setObserver {
            if (conferenceViewModel.hideName) {
                hideConferenceName()
            } else {
                showConferenceName(conferenceViewModel.name)
            }
        }
    }

    override fun onPause() {
        conferenceViewModel.removeObserver()
        super.onPause()
    }

    override fun onDetach() {
        dependencyManager = null
        super.onDetach()
    }

    private fun showConferenceName(name: String) {
        conferenceName.text = name
        conferenceName.visibility = View.VISIBLE
    }

    private fun hideConferenceName() {
        conferenceName.visibility = View.GONE
    }
}
