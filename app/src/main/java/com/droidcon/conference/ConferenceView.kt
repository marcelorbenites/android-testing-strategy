package com.droidcon.conference

interface ConferenceView {
    fun hideLoading()
    fun showLoading()
    fun hideError()
    fun showError()
    fun showConferenceName(name: String)
    fun hideConferenceName()
}
