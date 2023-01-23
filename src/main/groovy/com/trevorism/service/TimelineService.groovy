package com.trevorism.service

import com.trevorism.model.TimelineResponse
import com.trevorism.model.WorkHistoryItem

interface TimelineService {

    TimelineResponse generate(List<WorkHistoryItem> histories)
}
