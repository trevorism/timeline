package com.trevorism.service

import com.trevorism.model.TimelineResponse
import com.trevorism.model.WorkHistoryItem

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class DateToPixelTimelineService implements TimelineService {
    @Override
    TimelineResponse generate(List<WorkHistoryItem> histories) {
        LocalDate beginDate = getFirstDate(histories)
        LocalDate endDate = getLastDate(histories)
        int numberOfDays = ChronoUnit.DAYS.between(beginDate, endDate)
        
    }

    LocalDate getFirstDate(List<WorkHistoryItem> workHistoryItems) {
        null
    }

    LocalDate getLastDate(List<WorkHistoryItem> workHistoryItems) {
        null

    }
}
