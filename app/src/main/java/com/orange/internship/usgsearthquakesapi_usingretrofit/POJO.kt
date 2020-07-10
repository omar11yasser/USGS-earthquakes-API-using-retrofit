package com.orange.internship.usgsearthquakesapi_usingretrofit

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Properties(
    @SerializedName("mag")  var mag: BigDecimal,
    @SerializedName("place")  var place: String,
    @SerializedName("time") var time : Long,
    @SerializedName("tsunami") var tsunami : Int)

data class Feature(
    @SerializedName("type")  var type: String,
    @SerializedName("properties")  val properties: Properties
)

data class EarthquakeModel(
    @SerializedName("features")var features: List<Feature>
)

