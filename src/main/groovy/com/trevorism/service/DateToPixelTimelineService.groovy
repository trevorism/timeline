package com.trevorism.service

import com.trevorism.model.TimelineDate
import com.trevorism.model.TimelineResponse
import com.trevorism.model.WorkHistoryItem
import com.trevorism.model.WorkHistoryPixels

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class DateToPixelTimelineService implements TimelineService {

    public static final int MAX_PIXEL_LENGTH = 1000
    public static final int GAP_THRESHOLD_DAYS = 2
    public static final int KEY_DATE_THRESHOLD_PIXELS = 200

    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy")
    public static final DateTimeFormatter isoDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    public static final DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM/yyyy")
    public static final String GAP = "GAP"

    @Override
    TimelineResponse generate(List<WorkHistoryItem> histories) {
        List<WorkHistoryItem> cleaned = cleanup(histories)
        if (!cleaned)
            return new TimelineResponse()

        int totalNumberOfDays = getTotalNumberOfDays(cleaned)
        double scaleFactor = ((double) MAX_PIXEL_LENGTH / (double) totalNumberOfDays)
        int currentPixel = 0;


        List<WorkHistoryItem> sorted = sortByDateAscending(cleaned)
        List<WorkHistoryItem> fullHistory = fillInGaps(sorted)

        TimelineResponse response = new TimelineResponse()
        fullHistory.each {
            LocalDate beginDate = getLocalDate(it.startDate)
            LocalDate endDate = LocalDate.now()
            if (it.endDate)
                endDate = getLocalDate(it.endDate)

            int days = daysBetween(beginDate, endDate)
            int pixels = scaleFactor * days
            def whp = new WorkHistoryPixels(employer: it.employer, beginPixel: currentPixel, endPixel: currentPixel + pixels, color: it.color, beginMonthYear: monthYearFormatter.format(beginDate), endMonthYear: endMonthYearOrPresent(endDate))
            response.items.add(whp)
            currentPixel += pixels
        }

        response.items.each{
            int length = it.endPixel - it.beginPixel
            if(it.beginPixel == 0 || (length > KEY_DATE_THRESHOLD_PIXELS && it.beginPixel > 80)){
                response.timelineDates.add(new TimelineDate(dateString: it.beginMonthYear, pixelLocation: it.beginPixel))
            }
        }

        return response
    }

    private String endMonthYearOrPresent(LocalDate endDate) {
        if (daysBetween(endDate, LocalDate.now()) < GAP_THRESHOLD_DAYS) {
            return "Present"
        }
        return monthYearFormatter.format(endDate)
    }

    private List<WorkHistoryItem> sortByDateAscending(List<WorkHistoryItem> histories) {
        histories.sort { a, b ->
            getLocalDate(a.startDate) <=> getLocalDate(b.startDate)
        }
    }

    private int getTotalNumberOfDays(List<WorkHistoryItem> histories) {
        LocalDate beginDate = getFirstDate(histories)
        LocalDate endDate = getLastDate(histories)

        if (beginDate == null)
            throw new RuntimeException("Unable to compute timeline due to no begin or end date")

        if (endDate == null)
            endDate = LocalDate.now()

        daysBetween(beginDate, endDate)
    }

    private long daysBetween(LocalDate beginDate, LocalDate endDate) {
        Math.abs(ChronoUnit.DAYS.between(beginDate, endDate))
    }

    LocalDate getFirstDate(List<WorkHistoryItem> workHistoryItems) {
        getLocalDate(workHistoryItems.sort { getLocalDate(it.startDate) }.first().startDate)
    }

    LocalDate getLastDate(List<WorkHistoryItem> workHistoryItems) {
        def sortedDescending = workHistoryItems.sort { a, b -> getLocalDate(b.startDate) <=> getLocalDate(a.startDate) }.first()
        if (sortedDescending?.endDate)
            return getLocalDate(sortedDescending.endDate)
        return LocalDate.now()
    }

    LocalDate getLocalDate(String date) {
        try{
            return LocalDate.parse(date, dateFormatter)
        } catch (Exception e){
            return LocalDate.parse(date, isoDateFormatter)
        }
    }

    List<WorkHistoryItem> fillInGaps(List<WorkHistoryItem> workHistoryItems) {
        def itemsToAdd = []
        for (int i = 1; i < workHistoryItems.size(); i++) {
            LocalDate endDate = getLocalDate(workHistoryItems[i - 1].endDate)
            LocalDate startDate = getLocalDate(workHistoryItems[i].startDate)
            if (daysBetween(startDate, endDate) > GAP_THRESHOLD_DAYS) {
                itemsToAdd.add(new WorkHistoryItem(employer: GAP, startDate: workHistoryItems[i - 1].endDate, endDate: workHistoryItems[i].startDate, color: "#FFFFFF"))
            }
        }
        workHistoryItems.addAll(itemsToAdd)
        return sortByDateAscending(workHistoryItems)
    }

    List<WorkHistoryItem> cleanup(List<WorkHistoryItem> workHistoryItems) {
        workHistoryItems.removeAll {
            !it.startDate
        }
        return workHistoryItems
    }
}
