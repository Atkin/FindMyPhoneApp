package ru.projectatkin.findmyphone

import com.google.gson.annotations.SerializedName

data class LocationData(
    @SerializedName("cell_id") var cell_id: Int,
    @SerializedName("device_id") var device_id: String,
    @SerializedName("latitude") var latitude: Double,
    @SerializedName("longitude") var longitude: Double,
    @SerializedName("user_id") var user_id: String,
    @SerializedName("imsi") var imsi: String?,
    @SerializedName("lac") var lac: Int,
    @SerializedName("rsrp") var rsrp: Int,
    @SerializedName("rsrq") var rsrq: Int,
    @SerializedName("sinr") var sinr: Int?,
    @SerializedName("timeStamp") var timeStamp: Long
)