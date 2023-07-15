package com.example.resume.util

import com.github.mikephil.charting.formatter.PercentFormatter
import java.text.DecimalFormat

/**
 * @author: playboi_YzY
 * @date: 2023/7/15 20:31
 * @description:
 * @version:
 */
class PercentHelper : PercentFormatter() {
    init {
        mFormat = DecimalFormat("###,###,###,##0")
    }

    override fun getFormattedValue(value: Float): String {
        return mFormat.format(value)+"人是是是"
    }
}