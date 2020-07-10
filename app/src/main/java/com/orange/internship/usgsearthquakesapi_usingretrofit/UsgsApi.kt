package com.orange.internship.usgsearthquakesapi_usingretrofit

import android.content.Context

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET

import retrofit2.http.Query

import retrofit2.http.QueryMap

    // Set the interface for querying to the USGS API
interface UsgsApi {
    @GET("fdsnws/event/1/query?")
        fun  getEarthQuakes(
            @Query("format") format: String,
            @Query("starttime") startTime: String,
            @Query("endtime")  endTime: String,
            @Query("limit") limit :Int) : Call<EarthquakeModel>

    companion object {
        // Create and return the retrofit object on the launch of the application
        fun create(context: Context): UsgsApi {
            val retrofit =
                Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://earthquake.usgs.gov/").build()
            return retrofit.create(UsgsApi::class.java)
        }
    }
}