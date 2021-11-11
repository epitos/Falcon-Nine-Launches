package com.falconnine.utils

import com.falconnine.utils.Constants.API_DATE_FORMAT
import com.falconnine.utils.Constants.UI_DATE_FORMAT
import java.text.SimpleDateFormat

object DateUtils {

    fun formatDate(date: String): String {
        val originalFormat = SimpleDateFormat(API_DATE_FORMAT)
        val newFormat = SimpleDateFormat(UI_DATE_FORMAT)
        val originDate = originalFormat.parse(date)
        return newFormat.format(originDate)
    }
}