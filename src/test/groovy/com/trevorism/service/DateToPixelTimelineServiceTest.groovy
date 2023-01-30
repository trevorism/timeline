package com.trevorism.service

import com.trevorism.model.WorkHistoryItem
import org.junit.jupiter.api.Test

import java.time.LocalDate
import java.time.Month

class DateToPixelTimelineServiceTest {

    DateToPixelTimelineService service = new DateToPixelTimelineService()

    @Test
    void testGenerate(){
        def response = service.generate([new WorkHistoryItem(startDate: "1/12/2023", endDate: "1/19/2023"), new WorkHistoryItem(startDate:  "7/22/2022", endDate: "12/1/2022")])
        response.items.each {
            println it
        }
    }

    @Test
    void testGetFirstDate() {
        LocalDate localDate = service.getFirstDate([new WorkHistoryItem(startDate: "1/12/2023", endDate: "1/19/2023"), new WorkHistoryItem(startDate:  "7/22/2022", endDate: "12/1/2022")])
        assert localDate.year == 2022
        assert localDate.month == Month.JULY
        assert localDate.dayOfMonth == 22

    }

    @Test
    void testGetLastDate() {
        LocalDate localDate = service.getLastDate([new WorkHistoryItem(startDate: "1/12/2023", endDate: "1/19/2023"), new WorkHistoryItem(startDate:  "7/22/2022", endDate: "12/1/2022")])
        assert localDate.year == 2023
        assert localDate.month == Month.JANUARY
        assert localDate.dayOfMonth == 19

    }

    @Test
    void testGetLocalDate() {
        String str = "1/12/2023"
        LocalDate localDate = service.getLocalDate(str)
        assert localDate.year == 2023
        assert localDate.month == Month.JANUARY
        assert localDate.dayOfMonth == 12
    }
}
