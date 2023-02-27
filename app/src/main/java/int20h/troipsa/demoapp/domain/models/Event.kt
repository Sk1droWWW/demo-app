package int20h.troipsa.demoapp.domain.models

import java.time.LocalDateTime
import java.time.YearMonth


data class Event(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val epochDay: Long = startTime.toLocalDate().toEpochDay(),
    val eventType: EventType,
)

