package com.example.resume.bean

import com.google.gson.annotations.SerializedName


data class Exp(
    var year0 : Int,

    /**
     * 1-3
     */
    @SerializedName("year1~3")
    var `_$Year13189` : Int,

    /**
     * 3-5
     */
    @SerializedName("year4~5")
    var `_$Year45321` : Int,

    /**
     * >5
     */
    @SerializedName("year>5")
    var `_$Year563` : Int
)
