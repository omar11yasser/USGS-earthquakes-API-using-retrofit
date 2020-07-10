package com.orange.internship.usgsearthquakesapi_usingretrofit


import android.util.Log
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Response


class MainPresenter(val view: MainContract.View) : MainContract.Presenter, KoinComponent {
    //Inject apiService here
    private val mApiService: UsgsApi by inject()

    override fun getEartquakes(starttime: String, endtime: String) {
        if (!validate(starttime) || !validate(endtime))
            return
        view.showLoading()
        mApiService.getEarthQuakes( format = "geojson" ,startTime = starttime, endTime = endtime)
            .enqueue(object : retrofit2.Callback<EarthquakeModel?> {
                override fun onResponse(
                    call: Call<EarthquakeModel?>,
                    response: Response<EarthquakeModel?>
                ) {
                    if (!response.isSuccessful) {
                        return
                    }
                    val earthquakes: EarthquakeModel? = response.body()
                    val place = earthquakes!!.features[0].properties.place
                    Log.d(javaClass.simpleName , "The place that the earthquake happen is $place")
                    val featuresList : List<Feature> = earthquakes.features
                    view.showResult(
                        featuresList
                    )
                }

                override fun onFailure(call: Call<EarthquakeModel?>, t: Throwable) {

                }
            })
    }
}

    fun validate(id: String): Boolean {
        var validated = true

        if (id.isEmpty()) {
            validated = false
        }
        return validated
    }