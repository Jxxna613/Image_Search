package com.example.image_search

import android.content.Context
import com.example.image_search.Data.Constants.PREFSAVE_KEY
import com.example.image_search.Data.Constants.PREFSAVE_NAME
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object Utils {
    fun getDataTimes(
        timestamp: String?,
        fromFormatformat: String?,
        toFormatformat: String?
    ): String {
        var date: Date? = null
        var res = ""
        try {
            val format = SimpleDateFormat(fromFormatformat)
            date = format.parse(timestamp)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val dateFormat = SimpleDateFormat(toFormatformat)
        res = dateFormat.format(date)
        return res
    }

    fun saveLastSearch(context: Context, query: String) {
        val prefSave = context.getSharedPreferences(PREFSAVE_NAME, 0)
        prefSave.edit().putString(PREFSAVE_KEY, query).apply()
    }

    fun getLastSearch(context: Context) : String? {
        val prefSave = context.getSharedPreferences(PREFSAVE_NAME, 0)
        return prefSave.getString(PREFSAVE_KEY, null)
    }
}