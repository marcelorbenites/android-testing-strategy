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

class ConferenceFragment : Fragment(), ConferenceView {

    private var dependencyManager: DependencyManager? = null

    private val conferenceStateMachine by lazy {
        dependencyManager!!.conferenceStateMachine
    }

    private val mainDispatcher by lazy {
        dependencyManager!!.mainDispatcher
    }

    private val conferencePresenter by lazy {
        ConferencePresenter(mainDispatcher, this)
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
        conferenceStateMachine.addStateChangedListener(conferencePresenter)
    }

    override fun onPause() {
        conferenceStateMachine.removeStateChangedListener(conferencePresenter)
        super.onPause()
    }

    override fun onDetach() {
        dependencyManager = null
        super.onDetach()
    }

    override fun hideLoading() {
    }

    override fun showLoading() {
    }

    override fun hideError() {
    }

    override fun showError() {
    }

    override fun showConferenceName(name: String) {
        conferenceName.text = name
        conferenceName.visibility = View.VISIBLE
    }

    override fun hideConferenceName() {
        conferenceName.visibility = View.GONE
    }
}
