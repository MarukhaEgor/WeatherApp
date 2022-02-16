package com.example.weatherapp.ui.homefragments.secondfragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSecondHomeBinding
import com.example.weatherapp.model.CurrentDayWeatherData
import com.example.weatherapp.utils.Constants
import com.example.weatherapp.utils.Constants.TEMP_SYMBOL
import com.example.weatherapp.utils.ImageSelector
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_second_home.*
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*


class SecondHomeFragment : Fragment() {

    private lateinit var binding: FragmentSecondHomeBinding

    private val viewModel: SecondFragmentHomeViewModel by inject()

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getCurrentLocation()
            } else {
                Toast.makeText(requireContext(),
                    getString(R.string.permission_message),
                    Toast.LENGTH_LONG)
                    .show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSecondHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipe: SwipeRefreshLayout = binding.fgSwipe
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission.launch(
                Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            getCurrentLocation()
        }
        setListeners()
        observe()
        swipe.setOnRefreshListener {
            getCurrentLocation()
            swipe.isRefreshing = false
        }
    }

    private fun setListeners() {
        binding.ivSecondFragmentCurrentWeather.setOnClickListener {
            viewModel.goToTheWeeklyData()
        }
    }

    private fun observe() {
        with(viewModel) {
            weatherDetail.observe(viewLifecycleOwner) {
                show(it)
                binding.progress.visibility = View.GONE
            }
            navigationEvent.observe(viewLifecycleOwner, ::navigate)
        }
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun show(it: CurrentDayWeatherData) {
        binding.apply {
            tvCurrentTemp.text = it.main.temp.toString() + TEMP_SYMBOL
            tvCurrentHumidity.text =
                getString(R.string.humidity) + " " + it.main.humidity.toString() + "%"
            tvCurrentWind.text = it.wind.speed.toString() + " m/s"
            tvCurrentData.text = Constants.DAYS_ARRAY[Calendar.getInstance()
                .get(Calendar.DAY_OF_WEEK)] + "," + SimpleDateFormat("dd.MM.yyyy").format(Date()) + "\n" + it.name + "\n" + it.coord.lat.toString() + ", " + it.coord.lon.toString()
            tvCurrentTime.text =
                getString(R.string.last_upd_time) + " " + SimpleDateFormat("HH:mm:ss").format(Date())
            iv_second_fragment_current_weather.load(ImageSelector.selectImage(it.weather[0].icon))
        }
    }


    private fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }


    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        binding.progress.visibility = View.VISIBLE
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)
                p0.lastLocation.apply {
                    viewModel.getWeatherData(latitude.toFloat(), longitude.toFloat())
                    viewModel.getAllDayWeatherData(latitude.toFloat(), longitude.toFloat())
                    viewModel.saveLocation(latitude.toFloat(),
                        longitude.toFloat())
                }
                fusedLocationProviderClient.removeLocationUpdates(this)
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
            locationCallback,
            Looper.myLooper() ?: Looper.getMainLooper())
    }
}