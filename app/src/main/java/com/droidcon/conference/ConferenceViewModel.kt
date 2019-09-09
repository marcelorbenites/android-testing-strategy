package com.droidcon.conference

import com.droidcon.state.ViewModel

class ConferenceViewModel(
    var name: String = "",
    var loading: Boolean = false,
    var networkError: Boolean = false,
    var unknownError: Boolean = false
) : ViewModel()
