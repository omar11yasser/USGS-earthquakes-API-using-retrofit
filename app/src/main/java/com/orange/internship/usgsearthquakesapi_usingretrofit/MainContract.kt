package com.orange.internship.usgsearthquakesapi_usingretrofit

class MainContract {
    interface View{
        fun showLoading()
        fun hideLoading()
        fun showResult(feature: List<Feature>)
        fun showError(errorMessage :String)
    }

    interface Presenter{
        fun getEartquakes(starttime : String , endtime : String)
    }
}