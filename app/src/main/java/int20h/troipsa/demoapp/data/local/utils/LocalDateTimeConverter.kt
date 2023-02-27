package int20h.troipsa.demoapp.data.local.utils

import androidx.room.TypeConverter
import int20h.troipsa.demoapp.utils.DateConvertUtils
import java.time.LocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun toLong(dateTime: LocalDateTime?): Long? {
        return dateTime?.let { DateConvertUtils.convertToTimestamp(it) }
    }

    @TypeConverter
    fun fromLong(millis: Long?): LocalDateTime? {
        return millis?.let { DateConvertUtils.convertToLocalDateTime(it) }
    }
}
