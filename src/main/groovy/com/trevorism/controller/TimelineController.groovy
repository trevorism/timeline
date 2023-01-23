package com.trevorism.controller

import com.trevorism.model.TimelineResponse
import com.trevorism.model.WorkHistoryItem
import com.trevorism.service.DateToPixelTimelineService
import com.trevorism.service.TimelineService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("/api/timeline")
class TimelineController {

    private static final Logger log = LoggerFactory.getLogger(TimelineController)
    private TimelineService timelineService = new DateToPixelTimelineService()

    @Tag(name = "Timeline Operations")
    @Operation(summary = "Validates inputs and generates a timeline")
    @Post(value = "/", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    TimelineResponse generate(@Body List<WorkHistoryItem> items) {
        timelineService.generate(items)
    }
}
