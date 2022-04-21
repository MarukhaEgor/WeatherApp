package com.example.weatherapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import org.koin.android.ext.android.inject

class SplashFragment : Fragment() {

    private val viewModel: SplashFragmentViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        setObservers()
        viewModel.showSplash()

//        Handler(Looper.getMainLooper()).postDelayed({
//            if(onBoardingFinished()){
//                findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
//            }else{
//                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
//            }
//        }, 1000)
//
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun setObservers() = viewModel.navigationEvent.observe(viewLifecycleOwner, ::navigate)

    private fun navigate(direction: NavDirections) = findNavController().navigate(direction)



//    private fun onBoardingFinished(): Boolean {
//        val sharedPref = requireActivity().getSharedPreferences("OpenApp", Context.MODE_PRIVATE)
//        return sharedPref.getBoolean("OpenApp", false)
//    }

}