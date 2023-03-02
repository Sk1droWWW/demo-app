package int20h.troipsa.demoapp.utils.extension

import java.time.LocalDateTime
import java.time.LocalTime

fun LocalDateTime.formatToEventTime(): String {
    return "${this.hour}:${this.minute.toString().padStart(2, '0')}"
}

fun LocalDateTime.atTime(time: LocalTime): LocalDateTime {
    return this.withHour(time.hour).withMinute(time.minute)
}