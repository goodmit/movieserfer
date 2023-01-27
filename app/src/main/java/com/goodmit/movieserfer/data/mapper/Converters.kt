package com.goodmit.movieserfer.data.mapper

import androidx.room.TypeConverter
import com.goodmit.movieserfer.data.models.ImageEntity
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromImage(image: ImageEntity?): String? {
        return image?.url
    }

    @TypeConverter
    fun toImage(url: String?): ImageEntity? {
        return url?.let { ImageEntity(it) }
    }
}