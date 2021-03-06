package com.orange.internship.usgsearthquakesapi_usingretrofit.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orange.internship.usgsearthquakesapi_usingretrofit.EarthquakeModel
import com.orange.internship.usgsearthquakesapi_usingretrofit.Feature
import com.orange.internship.usgsearthquakesapi_usingretrofit.R
import java.text.SimpleDateFormat
import java.util.*

class EarthquakesVerticalRecyclerViewAdapter(private val context: Context , private val earthquakesFeaturesList : List<Feature> ) : RecyclerView.Adapter<EarthquakesVerticalRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row_earthquake_data_vertical_recycler_view , parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return earthquakesFeaturesList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prop = earthquakesFeaturesList[position].properties
        holder.magTextView.text = prop.mag.toString()
        holder.placeTextView.text = "Location: ${prop.place}"
        holder.timeTextView.text = "Time: ${transformTimestampIntoTextDate(prop.time)}"
        holder.tsunamiTextView.text = generateTsunamiAlertText(prop.tsunami)
    }

    private fun transformTimestampIntoTextDate(timestamp : Long) : String{
        val sdf = SimpleDateFormat("MM/dd/yyyy" , Locale.UK)
        return sdf.format(timestamp)
    }

    private fun generateTsunamiAlertText(tsunami : Int) : String{
        if (tsunami == 0){
            return "No tsunami expected!"
        }
        return "Tsunami expected!"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val timeTextView : TextView = itemView.findViewById(R.id.list_row_earthquake_data_vertical_recycler_view_time_text_view)
            val placeTextView: TextView =itemView.findViewById(R.id.list_row_earthquake_data_vertical_recycler_view_place_text_view)
            val magTextView: TextView = itemView.findViewById(R.id.list_row_earthquake_data_vertical_recycler_view_mag_text_view)
            val tsunamiTextView: TextView = itemView.findViewById(R.id.list_row_earthquake_data_vertical_recycler_view_tsunami_text_view)

    }
}