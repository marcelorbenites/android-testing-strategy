package com.droidcon

import com.droidcon.conference.ConferenceController
import com.droidcon.conference.ConferenceViewModel

interface DependencyManager {
    val conferenceViewModel: ConferenceViewModel
    val conferenceController: ConferenceController
}
