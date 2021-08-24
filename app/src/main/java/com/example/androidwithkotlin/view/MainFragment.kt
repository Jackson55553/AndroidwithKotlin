package com.example.androidwithkotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidwithkotlin.databinding.MainFragmentBinding
import com.example.androidwithkotlin.ui.model.AppState
import com.example.androidwithkotlin.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<AppState> { renderData(it) }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        binding.button.setOnClickListener { viewModel.requestData(binding.edi.text.toString()) }
    }

    private fun renderData(data: AppState) {
        when(data){
            is AppState.Success ->{
                val weatherData = data.weatherData
                binding.loadingLayout.visibility= View.GONE
            }
        }


        binding.message.text = data
    }

}