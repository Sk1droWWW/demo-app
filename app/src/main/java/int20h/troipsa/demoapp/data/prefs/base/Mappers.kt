package int20h.troipsa.demoapp.data.prefs.base

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import int20h.troipsa.demoapp.utils.DateConvertUtils
import int20h.troipsa.demoapp.utils.gson.LocalDateSerializer
import int20h.troipsa.demoapp.utils.gson.LocalDateTimeSerializer
import kotlinx.serialization.modules.SerializersModule
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.reflect.KClass

/**
 * Interface which used in [DataStoreValue]
 * for transform custom types to types, that can be stored in [androidx.datastore.core.DataStore]
 *
 * Some mappers defined in [Companion], but you can define own mappers
 */
interface DataStoreMapper<Store, Read> {
    fun toStoredValue(read: Read): Store
    fun fromStoredValue(store: Store): Read

    companion object {
        /**
         * Mapper, that let store [LocalDate] as timestamp
         * @see [java.time.Instant.toEpochMilli]
         */
        fun localDateToLong(): DataStoreMapper<Long, LocalDate> {
            return LocalDateToLongMapper()
        }

        /**
         * Mapper, that let store [LocalDateTime] as timestamp
         * @see [java.time.Instant.toEpochMilli]
         */
        fun localDateTimeToLong(): DataStoreMapper<Long, LocalDateTime> {
            return LocalDateTimeToLongMapper()
        }

        /**
         * Mapper, that let store any object as json string.
         * When stored json can't be parsed to object,
         * [com.google.gson.JsonSyntaxException] will be throw
         */
        inline fun <reified T: Any> anyToJson(): DataStoreMapper<String, T> {
            return jsonMapper(T::class)
        }

        internal fun <T> noMapper(): DataStoreMapper<T, T> {
            return DirectMapper()
        }

        @PublishedApi
        internal fun <T: Any> jsonMapper(clazz: KClass<T>): DataStoreMapper<String, T> {
            return JsonMapper(clazz)
        }
    }
}

private class DirectMapper<T> : DataStoreMapper<T, T> {
    override fun toStoredValue(read: T): T {
        return read
    }

    override fun fromStoredValue(store: T): T {
        return store
    }
}

private class LocalDateToLongMapper : DataStoreMapper<Long, LocalDate> {
    override fun toStoredValue(read: LocalDate): Long {
        return DateConvertUtils.convertToTimestamp(read)
    }

    override fun fromStoredValue(store: Long): LocalDate {
        return DateConvertUtils.convertToLocalDate(store)
    }
}

private class LocalDateTimeToLongMapper : DataStoreMapper<Long, LocalDateTime> {
    override fun toStoredValue(read: LocalDateTime): Long {
        return DateConvertUtils.convertToTimestamp(read)
    }

    override fun fromStoredValue(store: Long): LocalDateTime {
        return DateConvertUtils.convertToLocalDateTime(store)
    }
}

private class JsonMapper<T: Any>(private val clazz: KClass<T>) : DataStoreMapper<String, T> {

    private val json = Json {
        encodeDefaults = true
        serializersModule = SerializersModule {
            contextual(LocalDateTime::class, LocalDateTimeSerializer)
            contextual(LocalDate::class, LocalDateSerializer)
        }
    }

    @OptIn(InternalSerializationApi::class)
    override fun toStoredValue(read: T): String {
        return json.encodeToString(clazz.serializer(), read)
    }

    @OptIn(InternalSerializationApi::class)
    override fun fromStoredValue(store: String): T {
        return json.decodeFromString(clazz.serializer(), store)
    }

}