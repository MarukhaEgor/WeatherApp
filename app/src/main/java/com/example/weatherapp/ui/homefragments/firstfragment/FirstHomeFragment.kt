package com.example.weatherapp.ui.homefragments.firstfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentFirstHomeBinding
import com.example.weatherapp.ui.homefragments.firstfragment.adapter.FirstFragmentAdapter
import com.example.weatherapp.ui.homefragments.secondfragment.SecondFragmentHomeViewModel
import com.example.weatherapp.ui.homefragments.weeklyweather.adapter.WeekWeatherItemsAdapter
import org.koin.android.ext.android.inject


class FirstHomeFragment : Fragment() {

    private lateinit var binding: FragmentFirstHomeBinding

    private val viewModel: FirstHomeFragmentViewModel by inject()

    private val firstAdapter by lazy {
        FirstFragmentAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFirstHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        setObserve()
    }

    private fun setObserve() {
        viewModel.citiesData.observe(viewLifecycleOwner) { it ->
            it.let { firstAdapter.submitList(it) }
        }
    }

    private fun initRv() {
        binding.rvCitiesList.apply {
            adapter = firstAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }


}