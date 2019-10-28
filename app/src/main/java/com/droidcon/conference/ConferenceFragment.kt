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

    private val conferenceController by lazy {
        dependencyManager!!.conferenceController
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
                conferenceName.visibility = View.GONE
            } else {
                conferenceName.text = conferenceViewModel.name
                conferenceName.visibility = View.VISIBLE
            }

            errorText.visibility = if (conferenceViewModel.showError) View.VISIBLE else View.GONE
            retryButton.visibility = if (conferenceViewModel.showRetry) View.VISIBLE else View.GONE
            loadingProgressBar.visibility =
                if (conferenceViewModel.showLoading) View.VISIBLE else View.GONE
        }
        retryButton.setOnClickListener {
            conferenceController.loadConference()
        }
    }

    override fun onPause() {
        conferenceViewModel.removeObserver()
        retryButton.setOnClickListener(null)
        super.onPause()
    }

    override fun onDetach() {
        dependencyManager = null
        super.onDetach()
    }
}
