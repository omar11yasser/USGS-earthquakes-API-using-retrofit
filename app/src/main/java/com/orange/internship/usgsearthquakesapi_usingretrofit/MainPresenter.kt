package com.orange.internship.usgsearthquakesapi_usingretrofit


import android.util.Log
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Response
import java.lang.NullPointerException


class MainPresenter(val view: MainContract.View) : MainContract.Presenter, KoinComponent {
    //Inject apiService here
    private val mApiService: UsgsApi by inject()

    override fun getEartquakes(format : String ,starttime: String, endtime: String) {
        if (!validate(starttime) || !validate(endtime))
            return
        view.showLoading()
        mApiService.getEarthQuakes( format = format ,startTime = starttime, endTime = endtime)
            .enqueue(object : retrofit2.Callback<EarthquakeModel?> {
                override fun onResponse(
                    call: Call<EarthquakeModel?>,
                    response: Response<EarthquakeModel?>
                ) {
                    if (!response.isSuccessful) {
                        return
                    }
                    val earthquakes: EarthquakeModel? = response.body()
                    if (earthquakes == null){
                        Log.d(javaClass.simpleName , "Earthquake is null!")
                    } else Log.d(javaClass.simpleName , "Earthquake is not null!")
                    val featuresList : List<Feature>? = earthquakes?.features
                    view.showResult(
                        featuresList
                    )
                }

                override fun onFailure(call: Call<EarthquakeModel?>, t: Throwable)  {
                    Log.d(javaClass.simpleName ,  "onFailureCalled")
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