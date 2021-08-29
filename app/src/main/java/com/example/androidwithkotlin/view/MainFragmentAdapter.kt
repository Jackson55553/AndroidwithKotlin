package com.example.androidwithkotlin.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwithkotlin.data.Weather
import com.example.androidwithkotlin.databinding.MainRecyclerItemBinding

class MainFragmentAdapter : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>(){

    private var weatherData:List<Weather> = listOf()
    private var listener: MainFragment.onItemViewClickListener? = null

    fun setOnItemViewClickListener(listener:MainFragment.onItemViewClickListener ){
        this.listener = listener
    }

    fun remove(){
        listener = null
    }

    fun setWeather(data:List<Weather>){
        weatherData = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainRecyclerItemBinding.inflate(LayoutInflater.from(parent.context)
            ,parent
            ,false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(weatherData[position])
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

   inner class MainViewHolder(val binding: MainRecyclerItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(weather: Weather){
            binding.mainFragmentRecyclerItemTextView.text = weather.city.city
            binding.root.setOnClickListener {
                listener?.onItemViewClick(weather)
            }
        }
    }
}
