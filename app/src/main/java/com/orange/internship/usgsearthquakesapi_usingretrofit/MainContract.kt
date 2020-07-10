package com.orange.internship.usgsearthquakesapi_usingretrofit

import retrofit2.Response

class MainContract {
    interface View{
        fun showLoading()
        fun hideLoading()
        fun showResult(feature: List<Feature>, response: Response<EarthquakeModel?>)
        fun showError(errorMessage :String)
    }

    interface Presenter{
        fun getEartquakes(format: String , starttime : String , endtime : String)
    }
}