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

class EarthquakesVerticalRecyclerViewAdapter(private val context: Context , private val earthquakesFeaturesList : List<Feature>? ) : RecyclerView.Adapter<EarthquakesVerticalRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row_earthquake_data_vertical_recycler_view , parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return earthquakesFeaturesList!!.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val earthquake = earthquakesFeaturesList?.get(position)
        holder.magTextView.text = earthquake?.properties?.mag.toString()
        holder.placeTextView.text = earthquake?.properties?.place
        holder.timeTextView.text = earthquake?.properties?.time.toString()
        holder.tsunamiTextView.text = earthquake?.properties?.tsunami.toString()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val timeTextView : TextView = itemView.findViewById(R.id.list_row_earthquake_data_vertical_recycler_view_time_text_view)
            val placeTextView: TextView =itemView.findViewById(R.id.list_row_earthquake_data_vertical_recycler_view_place_text_view)
            val magTextView: TextView = itemView.findViewById(R.id.list_row_earthquake_data_vertical_recycler_view_mag_text_view)
            val tsunamiTextView: TextView = itemView.findViewById(R.id.list_row_earthquake_data_vertical_recycler_view_tsunami_text_view)

    }
}