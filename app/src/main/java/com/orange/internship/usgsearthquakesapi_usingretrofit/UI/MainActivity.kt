package com.orange.internship.usgsearthquakesapi_usingretrofit.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orange.internship.usgsearthquakesapi_usingretrofit.*
import com.orange.internship.usgsearthquakesapi_usingretrofit.Adapter.EarthquakesVerticalRecyclerViewAdapter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContract.View {

    // Networking

    // View widgets
    private lateinit var searchConstraintLayout : ConstraintLayout
    private lateinit var resultsConstraintLayout : ConstraintLayout
    private lateinit var startDateEditText: EditText
    private lateinit var endDateEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var earthquakesRecyclerView : RecyclerView
    private lateinit var newSearchButton : Button
    private lateinit var progressBar: ProgressBar

    // Utils & data-containers
    private lateinit var earthquakesList: List<Feature>
    lateinit var adapter : EarthquakesVerticalRecyclerViewAdapter
    private val mPresenter: MainPresenter by inject { parametersOf(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        earthquakesList = ArrayList<Feature>()

        // View widgets initialization
        searchConstraintLayout = findViewById(R.id.activity_main_search_constraint_layout)
        resultsConstraintLayout = findViewById(R.id.activity_main_results_constraint_layout)
        startDateEditText = findViewById(R.id.activity_main_start_time_edit_text)
        endDateEditText = findViewById(R.id.activity_main_end_time_edit_text)
        searchButton = findViewById(R.id.activity_main_search_button)
        earthquakesRecyclerView = findViewById(R.id.activity_main_results_recycler_view)
        newSearchButton = findViewById(R.id.activity_main_results_new_search_button)
        progressBar = findViewById(R.id.activity_main_results_progress_bar)
    }

    override fun onStart() {
        super.onStart()
        searchButton.setOnClickListener {
            mPresenter.getEartquakes(starttime = startDateEditText.text.toString() , endtime = endDateEditText.text.toString())
        }
    }

    private fun recyclerViewAndRecyclerAdapterSetUp(){
        adapter = EarthquakesVerticalRecyclerViewAdapter(applicationContext , earthquakesList)
        earthquakesRecyclerView.adapter = adapter
        earthquakesRecyclerView.layoutManager = LinearLayoutManager(applicationContext , RecyclerView.VERTICAL , false)
        earthquakesRecyclerView.hasFixedSize()
        Log.d(javaClass.simpleName , "recyclerViewAndRecyclerAdapterSetUp() finished executing")
    }

    override fun showLoading() {
        recyclerViewAndRecyclerAdapterSetUp()
        searchConstraintLayout.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        resultsConstraintLayout.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showResult(feature: List<Feature>) {
        Log.d(javaClass.simpleName , "ShowResult() called!")
        earthquakesList = feature
        adapter.notifyDataSetChanged()
    }

    override fun showError(errorMessage: String) {
        Log.d(javaClass.simpleName , "Error message: $errorMessage")

    }
}